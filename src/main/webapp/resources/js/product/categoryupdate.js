//카테고리
$('#topCategory').change(function () {
    //상위카테고리를 클릭할시 해당 하위카테고리 조회

    let selectItem = $('#topCategory').val()

    var topCategoryId = selectItem
    $.ajax({
        url: "/category/category/" + topCategoryId,
        method: "GET",
        success: function (subCategory){

            $('#subCategory').empty();
            for(let i = 0; i < subCategory.length; i++) {
                let subCategoryDTO = subCategory[i]
                let option = $('<button   value="' + subCategoryDTO.categoryId + '">' +
                    ''+  subCategoryDTO.categoryName +' <a href="/category/categoryDelete?categoryId='+subCategoryDTO.categoryId +' "><span class="icon_close"></span></a>'
              +  ' &nbsp'+'&nbsp' + '</button>')

                $("#subCategory").append(option)
            }

            $('#subCategory').niceSelect('update');
        }
    })

});