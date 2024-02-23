// vanilla js
document.querySelectorAll('.category-button').forEach(button => {
  button.addEventListener("click", event => {
    document.querySelector('#active-category-button').removeAttribute('id');
    button.setAttribute('id', 'active-category-button');
  })
})

// jquery js
// $('.category-button').each(function(index, value){
//   value.addEventListener("click", event => {
//     $('#active-category-button').removeAttr('id');
//     value.setAttribute('id', 'active-category-button');
//   })
// });