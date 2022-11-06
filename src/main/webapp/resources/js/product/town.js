function statusChange(statusItem) {


    var townName = $(statusItem).text();
    var range = $("#range").val();


    townName = $.trim(townName);


    $("#coordinateId").val(townName);

    if (confirm(townName + " 전방 " + range + "km 이내의 주변 상품을 보시겠습니까?") == true) {    //확인

        document.townadd.submit();

    } else {   //취소

        return false;

    }


}


$(function () {

    var $document = $(document);
    var selector = '[data-rangeslider]';
    var $element = $(selector);


    var textContent = ('textContent' in document) ? 'textContent' : 'innerText';


    function valueOutput(element) {
        var value = element.value + "km";
        var output = element.parentNode.getElementsByTagName('output')[0] || element.parentNode.parentNode.getElementsByTagName('output')[0];
        output[textContent] = value;
    }

    $document.on('input', 'input[type="range"], ' + selector, function (e) {
        valueOutput(e.target);
    });


    $document.on('click', '#js-example-disabled button[data-behaviour="toggle"]', function (e) {
        var $inputRange = $(selector, e.target.parentNode);

        if ($inputRange[0].disabled) {
            $inputRange.prop("disabled", false);
        } else {
            $inputRange.prop("disabled", true);
        }
        $inputRange.rangeslider('update');
    });


    $document.on('click', '#js-example-change-value button', function (e) {
        var $inputRange = $(selector, e.target.parentNode);
        var value = $('input[type="number"]', e.target.parentNode)[0].value + "km";

        $inputRange.val(value).change();
    });


    $document.on('click', '#js-example-change-attributes button', function (e) {
        var $inputRange = $(selector, e.target.parentNode);
        var attributes = {
            min: $('input[name="min"]', e.target.parentNode)[0].value,
            max: $('input[name="max"]', e.target.parentNode)[0].value,
            step: $('input[name="step"]', e.target.parentNode)[0].value
        };

        $inputRange.attr(attributes);
        $inputRange.rangeslider('update', true);
    });


    $document
        .on('click', '#js-example-destroy button[data-behaviour="destroy"]', function (e) {
            $(selector, e.target.parentNode).rangeslider('destroy');
        })
        .on('click', '#js-example-destroy button[data-behaviour="initialize"]', function (e) {
            $(selector, e.target.parentNode).rangeslider({polyfill: false});
        });


    $document
        .on('click', '#js-example-hidden button[data-behaviour="toggle"]', function (e) {
            var $container = $(e.target.previousElementSibling);
            $container.toggle();
        });


    $element.rangeslider({


        polyfill: false,

        // Callback function
        onInit: function () {
            valueOutput(this.$element[0]);
        },

        // Callback function
        onSlide: function (position, value) {
            console.log('onSlide');
            console.log('position: ' + position, 'value: ' + value);
        },

        // Callback function
        onSlideEnd: function (position, value) {
            console.log('onSlideEnd');
            console.log('position: ' + position, 'value: ' + value);
        }
    });

});