// console.log('hello~');

var searchResult = new Vue({
    el: '#search-result',
    data: {
        search_result: {},
    },
});

var wishListResult = new Vue({
    el: '#wish-list-result',
    data: {
        wish_list: {},
    },
    filters: {
        // filter로 쓸 fileter ID 지정
        yyyyMMddHHmmss: function(value) {
            // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
            if(!value) return '';

            // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
            var js_date = new Date(value);
            // 연도, 월, 일 추출
            var year = js_date.getFullYear();
            var month = js_date.getMonth() + 1;
            var day = js_date.getDate();
            var hour = js_date.getHours();
            var minute = js_date.getMinutes();
            var second = js_date.getSeconds();

            // 월, 일, 시간 , 분 의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
            if(month < 10) { month = '0' + month; }
            if(day < 10) { day = '0' + day; }
            if(hour < 10) { hour = '0' + hour; }
            if(minute < 10) { minute = '0' + minute; }
            if(second < 10) { second = '0' + second; }

            // 최종 포맷 (ex - '2021-10-08 13:11:12')
            return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
        },
    },
    methods: {
        deleteWishList(id) {
            console.log('wishlist delete id', id);

            // 서버 요청해서 실제 DB에서 삭제하고 화면에서도 사라지게 하기
            $.ajax({
                type: 'post',
                url: '/api/wishdelete/' + id,
                success: function(response, status, xhr) {
                    console.log('위시리스트 삭제 완료', response);   // WishListVO

                    getWishList();
                },
                error: function(request, status, error) {
                    alert('위시리스트 추가가 실패하였습니다');
                },
            });
        },
        addVisitCount(id) {
            console.log('wishlist addVisitCount id', id);

            // 서버 요청해서 실제 DB에서 방문횟수 올리고 화면 갱신
            $.ajax({
                type: 'post',
                url: '/api/wishvisit/' + id,
                success: function(response, status, xhr) {
                    console.log('위시리스트 방문횟수 증가', response);   // WishListVO

                    getWishList();
                },
                error: function(request, status, error) {
                    alert('위시리스트 방문횟수 증가가 실패하였습니다');
                },
            });
        },
    },
});

// 검색 버튼을 눌렀을 때 이벤트
$('#searchButton').click(function() {
    console.log('search btn click');

    const query = $('#searchBox').val();    // 검색어

    // 실제 backend에 /api/search 요청해서 데이터 가져오기(ajax)
    $.get(`/api/search?searchQuery=${query}`, function(response) {
        console.log('search response값', response);

        searchResult.search_result = response;

        const title = document.getElementById('wish-title');
        title.innerHTML = searchResult.search_result.title.replace(/<[^>]*>?/g, '');

        $('#search-result').show();
    });
});

// 검색창에서 enter키 눌렀을 시 이벤트 처리
$('#searchBox').on('keyup', function(e) {
    if (e.keyCode == 13) {      // enter
        $('#searchButton').click();
    }
});

// 위시리스트 추가 버튼 눌렀을 때
$('#wish-button').click(function() {
    console.log('with btn click');

    $.ajax({
        type: 'post',
        url: '/api/wishadd',
        data: JSON.stringify(searchResult.search_result),
        contentType: 'application/json',
        success: function(response, status, xhr) {
            console.log('위시리스트 추가 완료', response);   // WishListVO

            getWishList();
        },
        error: function(request, status, error) {
            alert('위시리스트 추가가 실패하였습니다');
        }
    })
});

// 위시리스트 목록 가져오기
function getWishList() {
    $.get(`/api/wishall`, function(res) {
        console.log('wishall response', res);  // List<WishListVO>
        wishListResult.wish_list = res;

        $('#wish-list-result').show();
    });
}

// 처음 페이지 로딩될 시 호출되는 jquery
$(document).ready(function () {
    $('#search-result').hide();
    $('#wish-list-result').hide();

    getWishList();
});
