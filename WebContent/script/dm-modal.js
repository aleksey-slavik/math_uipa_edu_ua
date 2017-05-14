/*
 * dm-modal.js v1.2
 *
 * Надёжный и функциональный плагин jQuery для вывода
 * модальных(всплывающих окон) на страницах сайтов;
 * возможность подключения в работу сразу нескольких модальных
 * блоков на одной странице, размер окон устанавливается непосредственно
 * в href атрибуте ссылок на вызов;
 *
 * Копирайт (c) 2014 @dobrovoi http://dbmast.ru
 */
$(document).ready(function(){
	//При нажатии на ссылку с классом poplight и href атрибута тега <a> с #
	$('a.poplight[href^=#]').click(function() {
		var popID = $(this).attr('rel'); //получаем имя окна, важно не забывать при добавлении новых менять имя в атрибуте rel ссылки
		var popURL = $(this).attr('href'); //получаем размер из href атрибута ссылки
				
		//запрос и переменные из href url
		var query= popURL.split('?');
		var dim= query[1].split('&');
		var popWidth = dim[0].split('=')[1]; //первое значение строки запроса
 
		//Fade in the Popup and add close button
		$('#' + popID).fadeIn().css({ 'width': Number( popWidth ) }).prepend('<a href="#" title="Close" class="close"></a>');
		
        //Определяем маржу(запас) для выравнивания по центру (по вертикали и горизонтали) - мы добавляем 80 к высоте / ширине с учетом отступов + ширина рамки определённые в css
		var popMargTop = ($('#' + popID).height() + 80) / 2;
		var popMargLeft = ($('#' + popID).width() + 80) / 2;
		
		//Устанавливаем величину отступа
		$('#' + popID).css({ 
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});
		
		//Добавляем полупрозрачный фон затемнения
		$('body').append('<div id="fade"></div>'); //div контейнер будет прописан перед тегом </body>.
		$('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn(); //полупрозрачность слоя, фильтр для тупого IE
		
		return false;
	});
	
	//Закрываем окно и фон затемнения
	$(document).on('click', 'a.close, #fade', function() { //закрытие по клику вне окна, т.е. по фону...
    $('#fade , .popup_block').fadeOut(function() {
        $('#fade, a.close').remove();  //плавно исчезают 
    });   
    return false;    
   });
    
});