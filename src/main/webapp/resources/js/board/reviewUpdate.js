$(document).ready(function (e){
    $("input[type='file']").change(function(e){

        $('#preview').empty();

        var files = e.target.files;
        var arr =Array.prototype.slice.call(files);

        //업로드 가능 파일인지 체크
        for(var i=0;i<files.length;i++){
            if(!checkExtension(files[i].name,files[i].size)){
                return false;
            }
        }
        preview(arr);
    });//file change

    function checkExtension(fileName,fileSize){
        var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
        var maxSize = 20971520;  //20MB

        if(fileSize >= maxSize){
            alert('파일 사이즈 초과');
            $("input[type='file']").val("");  //파일 초기화
            return false;
        }
        if(regex.test(fileName)){
            alert('업로드 불가능한 파일이 있습니다.');
            $("input[type='file']").val("");  //파일 초기화
            return false;
        }
        return true;
    }

    function preview(arr){
        arr.forEach(function(f){

            //파일명이 길면 파일명...으로 처리
            var fileName = f.name;
            if(fileName.length > 10){
                fileName = fileName.substring(0,7)+"...";
            }

            //div에 이미지 추가
            var str = '<div style="display: inline-flex; padding: 10px;"><li>';
            str += '<span>'+fileName+'</span><br>';

            //이미지 파일 미리보기
            if(f.type.match('image.*')){
                var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
                reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                    //str += '<button type="button" class="delBtn" value="'+f.name+'" style="background: red">x</button><br>';
                    str += '<img src="'+e.target.result+'" title="'+f.name+'" width=100 height=100 />';
                    str += '</li></div>';
                    $(str).appendTo('#preview');
                }
                reader.readAsDataURL(f);
            }else{
                str += '<img src="/resources/img/fileImg.png" title="'+f.name+'" width=100 height=100 />';
                $(str).appendTo('#preview');
            }
        });//arr.forEach
    }
});