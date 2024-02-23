// vanilla js

// const searchEL = document.querySelector('.search');
// const searchInputEL = document.querySelector('.input');

// searchInputEL.addEventListener('click', function() {
//   searchInputEL.fucus();
// });



// searchInputEL.addEventListener('focus',function(){
//   searchInputEL.setAttribute('placeholder', '통합검색');
// });

// searchInputEL.addEventListenerI('blur', function(){
//   searchInputEL.setAttribute('placeholder', '');
// })




//J쿼리
// const searchEL= $('.search');
// const searchInputEL= $('.search');

// input 과 돋보기 아이콘을 클릭했을 때
$('.search').click(function() {
  $('input').focus();
});


// input 포커스가 될 때
$('input').focus(function() {
  $('input').attr('placeholder', '통합검색')
});

// input 포커스가 풀릴때
$('input').blur(function() {
  $('input').attr('placeholder', '')
});


