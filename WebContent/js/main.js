$('body').on('click', '#with_pass', function() {
	if($(this).attr('vis') == 1) {
		$("#show_pass").hide();
		$("#with_pass").attr('vis', 0);
	} else {
		$("#show_pass").show();
		$("#with_pass").attr('vis', 1);
	}
});