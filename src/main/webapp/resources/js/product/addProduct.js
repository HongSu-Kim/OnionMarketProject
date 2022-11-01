//카테고리
$('#topCategory').change(function () {
    let digital = ["모바일", "가전제품", "오디오/영상/관련기기", "PC/노트북", "게임/타이틀", "카메라/DSLR", "PC부품/저장장치"]
    let furniture = ["가구", "인테리어"]
    let life = ["주방용품", "생활용품", "식품", "산업용품"]
    let child = ["베이비의류(0~2세)", "여아의류(3~6세)", "여주니어의류(7세~)", "남아의류(3~6세)", "남주니어의류(7세~)", "유아동신발/잡화", "교육/완구/인형", "유아동용품", "이유용품/유아식기"]
    let man = ["패딩/점퍼", "코트", "맨투맨", "후드티/후드집업", "티셔츠", "셔츠", "가디건", "니트/스웨터", "바지", "청바지", "반바지", "자켓", "정장", "조끼/트레이닝"]
    let woman = ["패딩/점퍼", "코트", "맨투맨", "후드티/후드집업", "티셔츠", "셔츠", "가디건", "니트/스웨터", "바지", "청바지", "반바지", "자켓", "정장", "조끼/트레이닝"]
    let bags = ["남성가방", "여성가방", "여행용"]
    let beauty = ["스킨케어", "색조메이크업", "바디/헤어케어", "향수/아로마", "네일아트/케어", "미용소품/기기", "다이어트/이너뷰티", "남성 화장품", "여성 화장품"]
    let sports = ["골프", "캠핑", "낚시", "축구", "야구", "농구", "탁구", "당구", "볼링", "테니스", "자전거", "등산/클라이밍", "헬스/요가/필라테스", "배드민턴"]
    let game = ["CD/DVD/LP", "악기", "게임CD"]
    let book = ["도서", "문구", "기프티콘/쿠폰", "상품권", "티켓"]
    let pet = ["강아지 용품", "강아지 사료/간식", "기타(강아지)", "고양이 용품", "고양이 사료/간식", "기타(고양이)", "기타(반려동물 용품)", "기타(반려동물 사료/간식)"]
    let plant = ["꽃", "다육이/선인장", "관상수/나무", "허브", "난초", "채소/과일", "수경식물", "에어플랜트"]
    let etc = ["기타상품"]

    let selectItem = $('#topCategory').val()
    let changeItem

    if (selectItem == "디지털/가전") {
        changeItem = digital
    } else if (selectItem == "가구/인테리어") {
        changeItem = furniture
    } else if (selectItem == "생활/가공식품") {
        changeItem = life
    } else if (selectItem == "유아동") {
        changeItem = child
    } else if (selectItem == "남성의류") {
        changeItem = man
    } else if (selectItem == "여성의류") {
        changeItem = woman
    } else if (selectItem == "가방/잡화") {
        changeItem = bags
    } else if (selectItem == "뷰티/미용") {
        changeItem = beauty
    } else if (selectItem == "스포츠/레저") {
        changeItem = sports
    } else if (selectItem == "게임/음반") {
        changeItem = game
    } else if (selectItem == "도서/티켓") {
        changeItem = book
    } else if (selectItem == "반려동물용품") {
        changeItem = pet
    } else if (selectItem == "식물") {
        changeItem = plant
    } else if (selectItem == "기타") {
        changeItem = etc
    }  else {
        return
    }

    $('#subCategory').empty();

    for (let i = 0; i < changeItem.length; i++) {
        let option = $('<option value="' + changeItem[i] + '">' + changeItem[i] + '</option>')
        $('#subCategory').append(option)
    }
    $('#subCategory').niceSelect('update');
});
/*상품 검증*/
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
            subject: {
                required: "제목은 필수 항목입니다."
            },
            content: {
                required: "설명은 필수 항목입니다.",
                //minLength: "최소 글자수는 10자 이상입니다.",
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
            '<input type="file" name="fileList" id="fileList"><button type="button" class="btnRemove" style="border-width: thin;">삭제</button>'
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

//이미지 파일 사이즈 지정
$.validator.addMethod('filesize', function(value, element, param) {
    return this.optional(element) || (element.files[0].size <= param)
}, 'File size must be less than {0} bytes');

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

//상위카테고리를 클릭할시 해당 하위카테고리 조회
$("#category").change(function (){

    $.ajax({
        url: "/product/category",
        method: "GET",
        data:({ categoryId :$("#category").val()}   ),
        datatype : "json",
        success: function (subCategory){

            for(var i = 0; i < subCategory.length; i++) {
                let option = $('<option value="' + subCategory.get[i].getId() + '">' + subCategory.get[i].getCategoryName() + '</option>')

                $("#category").append(option)





            }
        }

    }).fail(function () {

    })
})