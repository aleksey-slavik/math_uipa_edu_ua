$(function() {
	var current = 0;

	var loaded = 0;
	for (var i = 1; i < 4; ++i)
		$('<img />')
				.load(
						function() {
							++loaded;
							if (loaded == 3) {
								$('#bg1,#bg2,#bg3')
										.mouseover(
												function(e) {

													var $this = $(this);
													if ($this.parent().index() == current)
														return;
													var item = e.target.id;
													if (item == 'bg1'
															|| current == 2)
														$(
																'#menu .sub'
																		+ parseInt(current + 1))
																.stop()
																.animate(
																		{
																			backgroundPosition : "(-266px 0)"
																		},
																		300,
																		function() {
																			$(
																					this)
																					.find(
																							'li')
																					.hide();
																		});
													else
														$(
																'#menu .sub'
																		+ parseInt(current + 1))
																.stop()
																.animate(
																		{
																			backgroundPosition : "(266px 0)"
																		},
																		300,
																		function() {
																			$(
																					this)
																					.find(
																							'li')
																					.hide();
																		});

													if (item == 'bg1'
															|| current == 2) {
														$('#menu > li')
																.animate(
																		{
																			backgroundPosition : "(-800px 0)"
																		}, 0)
																.removeClass(
																		'bg1 bg2 bg3')
																.addClass(item);
														move(1, item);
													} else {
														$('#menu > li')
																.animate(
																		{
																			backgroundPosition : "(800px 0)"
																		}, 0)
																.removeClass(
																		'bg1 bg2 bg3')
																.addClass(item);
														move(0, item);
													}
													if (current == 2
															&& item == 'bg1') {
														$(
																'#menu .sub'
																		+ parseInt(current))
																.stop()
																.animate(
																		{
																			backgroundPosition : "(-266px 0)"
																		}, 300);
													}
													if (current == 0
															&& item == 'bg3') {
														$(
																'#menu .sub'
																		+ parseInt(current + 2))
																.stop()
																.animate(
																		{
																			backgroundPosition : "(266px 0)"
																		}, 300);
													}
													current = $this.parent()
															.index();
													$(
															'#menu .sub'
																	+ parseInt(current + 1))
															.stop()
															.animate(
																	{
																		backgroundPosition : "(0 0)"
																	},
																	300,
																	function() {
																		$(this)
																				.find(
																						'li')
																				.fadeIn();
																	});
												});
							}
						}).attr('src', 'images/' + i + '.jpg');
	function move(dir, item) {
		if (dir) {
			$('#bg1').parent().stop().animate({
				backgroundPosition : "(0 0)"
			}, 200);
			$('#bg2').parent().stop().animate({
				backgroundPosition : "(-266px 0)"
			}, 300);
			$('#bg3').parent().stop().animate({
				backgroundPosition : "(-532px 0)"
			}, 400, function() {
				$('#menuWrapper').removeClass('bg1 bg2 bg3').addClass(item);
			});
		} else {
			$('#bg1').parent().stop().animate({
				backgroundPosition : "(0 0)"
			}, 400, function() {
				$('#menuWrapper').removeClass('bg1 bg2 bg3').addClass(item);
			});
			$('#bg2').parent().stop().animate({
				backgroundPosition : "(-266px 0)"
			}, 300);
			$('#bg3').parent().stop().animate({
				backgroundPosition : "(-532px 0)"
			}, 200);
		}
	}
});