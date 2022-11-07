
function view(arg){
	$(".time1, .time2, .time3, .time4, .ad1, .ad2, .ad3, .ad4").css("display","none");
	if(arg=="0") {
		$(".time2, .ad2").css("display","block");
		viewcount = 1;
	}
	else if(arg=="1") {
		$(".time3, .ad3").css("display","block");
		viewcount = 2;
	}
	else if(arg=="2") {
		$(".time4, .ad4").css("display","block");
		viewcount = 3;
	}
	else if(arg=="3") {
		$(".time1, .ad1").css("display","block");
		viewcount = 0;
	}
}
var viewcount = 0;
var rtcarousel = setInterval(function(){ view(viewcount) },5000);

$("#best_search").mouseenter(function() {
	clearInterval(rtcarousel);
});

$("#best_search").mouseleave(function() {
	rtcarousel = setInterval(function(){ view(viewcount) },5000);
});
