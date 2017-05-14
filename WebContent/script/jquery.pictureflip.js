(function($) {

    $.fn.extend({

        pictureflip: function(options) {

            var defaults = {
                time: 1000,
                start: 1
            }

            var options = $.extend(defaults, options);

            return this.each(function() {

                var o = options;
                var obj = $(this);

                // Some variables to get started
                var time = o.time;
                var imageNumber = $('div[id^="image"]', obj).length;
                var animated = 0;

                // Append UI
                obj.append('<div class="back-arrow"></div><div class="forward-arrow"></div><div class="moving-div hide"><span></span><span></span></div><div class="buttons"></div>');
                obj.addClass('flipbook');

                // Add circle buttons according to how many images there are
                while (imageNumber > 0) {
                    $('<span class="button-' + imageNumber + '"></span>').appendTo($('.buttons'));
                    --imageNumber;
                }

                // Fade in the content text 
                $('div[id^="image-"]:first .content', obj).fadeIn();

                // We'll add this function later on!				
                selectCircle();

                // Change z index to descending so we don't have to worry about it
                function zIndexFix() {

                    $('[id^="image"]').each(function(index) {
                        zindex = index * -1;
                        $(this).css('zIndex', zindex);
                    });

                }

                // Restarts the slides to the regular position so they're ready to flip
                // again!
                function restartSlides() {

                    var $moveFirst = $('.moving-div div:first').attr('class').split('-')[1];
                    var $moveLast = $('.moving-div div:last').attr('class').split('-')[1];

                    $('> .moving-div div:first', obj).appendTo($('> #image-' + $moveFirst, obj));
                    $('> .moving-div div:last', obj).appendTo($('> #image-' + $moveLast, obj));

                    $('.moving-div').removeClass('rotateYForward rotateYBackward beginMove').removeAttr('style');

                }

                // Animate the shadow to give a sense of depth. 
                function shadowAnimate(time) {

                    $('.moving-div span').animate({
                        opacity: 1
                    }, time / 2);

                    setTimeout(function() {

                        $('.moving-div span').animate({
                            opacity: 0
                        }, time / 2);

                    }, time / 2);

                }

                // Selects the appropriate circle based on the image currently selected.
                function selectCircle() {
                    var imageID = parseFloat($('> div[id^="image"]:first', obj).attr('id').split('-')[1]);

                    $('.buttons span').removeClass('selected');
                    $('.buttons .button-' + imageID).addClass('selected');

                }

                $('> div[id^="image"]', obj).each(function() {

                    var $class = $(this).attr('id');
                    var $background = $(this).css('background');

                    // Append two images to the main div
                    $(this).append('<div class="' + $class + '-fstart"> </div> <div class="' + $class + '-fend"> </div>');


                });

                // The animation function
                function animation(prev, time, buttons) {

                    // Fade out the content text
                    $('div[id^="image-"]:first .content', obj).fadeOut(50);

                    // If the animation isn't running, then we can animate
                    if (animated == 0) {
                        // The animation is now running
                        animated = 1;

                        // The next slide to be shown.
                        if (prev != true) {
                            var $nextSlide = $('div[id^="image-"]:first ~ div[id^="image-"]:first', obj).attr('id');
                        } else {
                            var $nextSlide = $('div[id^="image-"]:last', obj).attr('id');
                        }

                        // The current slide
                        var $thisSlide = $('div[id^="image-"]:first', obj).attr('id');

                        // If 3D Transforms are not supported then we just default back to
                        // fade in slides. These will not have the 3D effect but  it  will
                        // still be usable.

                        if (Modernizr.csstransforms3d == "" || (navigator.userAgent.indexOf('Safari') != -1 && navigator.userAgent.indexOf('Chrome') == -1)) {

                            $('> #' + $nextSlide, obj).prependTo(obj);
                            if (prev != true) {
                                $('> #' + $thisSlide, obj).appendTo(obj);
                            }
                            animated = 0;
                            zIndexFix();
                            selectCircle();

                            $('div[id^="image-"]:first .content', obj).fadeIn();

                            return false;

                        }

                        // If it is the forward button
                        if (prev != true) {

                            // Append the correct divs to the moving div
                            $('> #' + $nextSlide + ' > div[class$="-fstart"], > #' + $thisSlide + ' > div[class$="-fend"]', obj).appendTo($('.moving-div'));


                            // Rotate the moving div holder and change the time
                            // based on what is set via Javascript
                            $('.moving-div', obj).addClass('rotateYForward').css({

                                '-webkit-transition-duration': time / 1000 + 's',
                                '-moz-transition-duration': time / 1000 + 's',
                                '-ms-transition-duration': time / 1000 + 's',
                                '-o-transition-duration': time / 1000 + 's',
                                'transition-duration': time / 1000 + 's'

                            });

                            // Move the divs around so that the next div is on top
                            // and the last div is on the bottom
                            $('> #' + $nextSlide, obj).prependTo(obj);
                            $('> #' + $thisSlide, obj).appendTo(obj);

                        } else {
                            // Else this is the back button
                            // So put the divs in the correct order so we can create
                            // the illusion of flipping
                            $('> #' + $thisSlide, obj).prependTo($(obj));
                            $('> #' + $nextSlide, obj).insertAfter($('div[id^="image-"]:first', obj));

                            // Then append the correct divs to the moving div
                            $('> #' + $nextSlide + ' > div[class$="-fend"], > #' + $thisSlide + ' > div[class$="-fstart"]', obj).appendTo($('.moving-div'));

                            // Fix the z-index real quick since we've been moving stuff around	
                            zIndexFix();

                            // Rotate the moving div holder
                            $('.moving-div').addClass('beginMove');

                            // A very small timeout so that this function does not interfere with 
                            // other functions
                            setTimeout(function() {

                                // Rotate!
                                $('.moving-div').addClass('rotateYBackward').css({

                                    '-webkit-transition-duration': time / 1000 + 's',
                                    '-moz-transition-duration': time / 1000 + 's',
                                    '-ms-transition-duration': time / 1000 + 's',
                                    '-o-transition-duration': time / 1000 + 's',
                                    'transition-duration': time / 1000 + 's'

                                });

                            }, time / 50);

                        }

                        // Animate the shadow
                        shadowAnimate(time);

                        // Another timeout, to be run at the end of the animation
                        setTimeout(function() {
                            // Put the slides back to their default classes and values
                            restartSlides();

                            if (buttons != true) {
                                $('.moving-div').addClass('hide');
                            }

                            // Prepend accordingly
                            if (prev == true) {
                                $(' > #' + $nextSlide, obj).prependTo($(obj));
                            }

                            // Fix up the z-index and change the circle
                            zIndexFix();
                            selectCircle();

                            // The animation is now over
                            animated = 0;

                            // Fade in the content!
                            $('div[id^="image-"]:first .content', obj).fadeIn();


                        }, time);

                    }


                }


                $('.forward-arrow').click(function() {
                    $('.moving-div').removeClass('hide');
                    setTimeout(function() {
                        animation(false, time);
                    }, 1);
                });

                $('.back-arrow').click(function() {
                    $('.moving-div').removeClass('hide');
                    setTimeout(function() {
                        animation(true, time);
                    }, 1);
                });

                // When clicking buttons..
                $('.buttons span[class^="button-"]', obj).click(function() {

                    // Remove the hide class from the moving div
                    $('.moving-div').removeClass('hide');

                    // Get the button ID
                    var buttonID = parseFloat($(this).attr('class').split('-')[1]);

                    // Get the current iamge ID
                    var currentImageID = parseFloat($('> div[id^="image"]:first', obj).attr('id').split('-')[1]);

                    // Calculate a faster time
                    var reduxTime = time / 10;

                    // And set an interval in a variable using the rotateImages function
                    var interval = setInterval(rotateImages, reduxTime);


                    function rotateImages() {

                        // Update the current image everytime the interval is run
                        currentImageID = parseFloat($('> div[id^="image"]:first', obj).attr('id').split('-')[1]);

                        // If the button number is equal to the current image number
                        if (buttonID == currentImageID) {
                            // The animation is over, clear in the interval
                            clearInterval(interval);
                            // Hide moving-div
                            $('.moving-div').addClass('hide');
                            // Cancel the function!
                            return false;
                        } else {
                            // Otherwise keep running the animation
                            animation(false, reduxTime, true);
                        }

                    }

                });

                // Make sure to fix the z-index too!
                zIndexFix();

                // Oh, and change the start image if the user defines it.
                $('.buttons .button-' + o.start, obj).click();

            });

        }

    });

})(jQuery);