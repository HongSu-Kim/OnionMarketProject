/*상품 검증*/

//이미지 파일 사이즈 지정
$.validator.addMethod('filesize', function(value, element, param) {
    return this.optional(element) || (element.files[0].size <= param)
}, 'File size must be less than {0} bytes');

$(document).ready(function () {
    //$('#registerForm').validate(); //유효성 검사를 적용
    // validate signup form on keyup and submit
    $('#productForm').validate({

        rules: {
            townId: "required",
            categoryId: "required",
            subject: "required",
            content: { required:true },
            price: { required:true, digits:true, min:100, max:999999},
            fileList: { required:true, filesize: 5*1024*1024 }
        },
        messages: {
            townId: {
                required: "동네를 선택해주세요."
            },
            categoryId: {
                required: "카테고리를 선택해주세요."
            },
            subject: {
                required: "제목은 필수 항목입니다."
            },
            content: {
                required: "설명은 필수 항목입니다."
                // minLength: "최소 글자수는 10자 이상입니다.",
                // maxLength: "최대 글자수는 250입니다."
            },
            price: {
                required: "가격을 입력해주세요.",
                digits: "숫자만 입력 가능합니다.",
                min: "최소 가격은 100원 이상입니다.",
                max: "최대 가격은 999,999원입니다."
            },
            fileList: {
                required: "이미지는 최소 1개 이상입니다.",
                filesize: "이미지 최대 크기는 5MB입니다."
            }
        }
    });
});

//파일 리스트 추가
$(document).ready(function() {
    $('.btnAdd').click(function () {
        $('.addInput').append(
            '<input type="file" name="fileList" id="fileList"><button type="button" class="btnRemove">삭제</button><br>'
        );//input file
        $('.btnRemove').on('click',function(){//this='.btnRemove'
            $(this).prev().remove();// .prev()=input file을 가리키고 remove()실행
            $(this).next().remove();//<br> 삭제
            $(this).remove();//버튼 삭제
        });
    });
});

function setImageFromFile(input, expression) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
    $(expression).attr('src', e.target.result);
};
    reader.readAsDataURL(input.files[0]);
    };
};

//상품 내용 글자수 세기
$(function() {
    $('#content').keyup(function (e){
        var content = $(this).val();
        // CSS의 길이 단위 중 em과 rem은 상대적으로 크기를 정한다.
        $(this).height(((content.split('\n').length + 1) * 1.5) + 'em');
        $('#counter').html(content.length + '/250');
    });
    $('#content').keyup(); // 처음 세팅
});