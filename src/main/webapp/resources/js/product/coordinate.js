function sendIt() {
    var f = document.searchForm;

    f.action = "cp/login";
    f.submit();
}

selectMapList();

//검색한 주소의 정보를 insertAddress 함수로 넘겨준다.
function searchAddressToCoordinate(address) {
    naver.maps.Service.geocode({
        query: address
    }, function (status, response) {
        if (status === naver.maps.Service.Status.ERROR) {
            return alert('Something Wrong!');
        }
        if (response.v2.meta.totalCount === 0) {
            return alert('올바른 주소를 입력해주세요.');
        }
        var htmlAddresses = [],
            item = response.v2.addresses[0],
            point = new naver.maps.Point(item.x, item.y);
        if (item.roadAddress) {
            htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
        }
        if (item.jibunAddress) {
            htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
        }
        if (item.englishAddress) {
            htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
        }

        insertAddress(item.roadAddress, item.x, item.y);

    });
}

// 주소 검색의 이벤트
$('#address').on('keydown', function (e) {
    var keyCode = e.which;
    if (keyCode === 16) { // Enter Key
        searchAddressToCoordinate($('#address').val());
    }
});
$('#submit').on('click', function (e) {
    e.preventDefault();
    searchAddressToCoordinate($('#address').val());
});
naver.maps.Event.once(map, 'init_stylemap', initGeocoder);


//검색정보를 테이블로 작성해주고, 지도에 마커를 찍어준다.
function insertAddress(address, latitude, longitude) {
    var townName = "";
    var Longitude = "";
    var Latitude = "";


    townName += address
    Longitude += latitude
    Latitude += longitude


    $('#townName').append(townName);//동
    $('#Longitude').append(Longitude);//경도
    $('#Latitude').append(Latitude);//위도


    var map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(longitude, latitude),
        zoom: 16
    });
    var marker = new naver.maps.Marker({
        map: map,
        position: new naver.maps.LatLng(longitude, latitude),
    });


}

//지도를 그려주는 함수
function selectMapList() {

    var map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(37.4988, 127.0319),
        zoom: 16
    });
}


// 지도를 이동하게 해주는 함수
function moveMap(len, lat) {
    var mapOptions = {
        center: new naver.maps.LatLng(len, lat),
        zoom: 16,
        mapTypeControl: true
    };
    var map = new naver.maps.Map('map', mapOptions);
    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(len, lat),
        map: map
    });
}