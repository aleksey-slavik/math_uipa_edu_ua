package ua.uipa.math.web.servlet.departm;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String LINK = "http://math.uipa.edu.ua/math/conferences?page=1";
	private static final String[] HISTORY_PRE_UA = {
			"Співробітники кафедри брали участь у міжнародних конференціях у Німеччині, Сінгапурі, Чехії, Росії, Норвегії, Польщі, Великобританії, Китаї, Болгарії, Угорщини. Кафедра підтримує наукові зв'язки з Бердянським державним педагогічним університетом, Харківським національним університетом ім. В.Н. Каразіна, Харківським національним університетом радіоелектроніки, НТУ «Харківський політехнічний інститут», Інститутом проблем машинобудування ім. О.М. Підгорного НАН України, Донецьким національним університетом, Рівненським національним університетом водного господарства та природокористування, Тернопільським технічним університетом, Київським національним університетом ім. Т.Г. Шевченка, Інститутом кібернетики НАН України ",
			"детальніше" };
	private static final String[] HISTORY_PRE_RU = { 
			"Сотрудники кафедры принимали участие в международных конференциях в Германии, Сингапуре, Чехии, России, Норвегии, Польше, Великобритании, Китае, Болгарии, Венгрии. Кафедра поддерживает научные связи с Бердянским государственным педагогическим университетом, Харьковским национальным университетом им. В.Н. Каразина, Харьковским национальным университетом радиоэлектроники, НТУ «Харьковский политехнический институт», Институт проблем машиностроения им. А.Н. Подгорного НАН Украины, Донецким национальным университетом, Ровенским национальным университетом водного хозяйства и природопользования, Тернопольским техническим университетом, Киевским национальным университетом им. Т.Г. Шевченко, Институтом кибернетики НАН Украины ",
			"подробнее" };
	private static final String[] HISTORY_PRE_EN = { 
			"Employees of the department took part in international conferences in Germany, Singapore, Czech Republic, Russia, Norway, Poland, Great Britain, China, Bulgaria, Hungary. The department maintains scientific relations with the Berdyansk State Pedagogical University, Kharkiv National University, Kharkov National University of Radio Electronics, NTU «Kharkov Polytechnic Institute», Institute of Mechanical Engineering Problems, Donetsk National University, Rivne National University of Water Management and Nature Management, Ternopil Technical University, Kyiv National University, Institute of Cybernetics of the National Academy of Sciences of Ukraine ",
			"more" };
	private static final String[] HISTORY_UNDER_UA = {
			"У 1958 р. була заснована кафедра вищої математики. Першим її завідувачем з 1958 по 1962 рр. був кандидат фіз.-мат. наук, доцент Іван Олександрович Наумов. Під його керівництвом було складено перші програми з курсу вищої математики.",
			"Наступником І.О. Наумова став канд. фіз.-мат. наук, доцент Тарас Григорович Іваницький, який очолював кафедру з 1962 по 1964 рр. У той час на кафедрі працювали В.Н. Жуковська, С.Б. Веселовський і Т.С. Коваль. У 1963 р. на кафедру прийшла працювати асистент Т.Н. Трикова. Гордістю колективу були такі талановиті математики: доктор фіз.-мат. наук, професор Г.І. Дрінфельд, який з 1964 по 1965 р. керував кафедрою; доктор фіз.-мат. наук, професор Б.Я. Левін – зав. кафедрою з 1965 по 1966 рр. У різний час кафедрою завідували В.Н. Жуковська, П.І. Бєлік, В.Н. Кібер, А.А. Челідзе. В період з 1968 по 1970 р. колектив поповнився молодими співробітниками, в числі яких Л.С. Лобанова, Н.Ф. Бедрицька, Ю.І. Созонов, Є.А. Ганенко, М.А. Мілітіді. У складі кафедри працювали І.М. Однопозова, С.І. Пархоменко, С.І. Боліславская, А.П. Яхно, С.Г.Мільграм.",
			"У цей же час на кафедру прийшов видатний вчений кандидат фіз.-мат. наук Віктор Федорович Шукайло. Під його керівництвом було отримано основоположні результати демографічних досліджень і побудовано відповідні математичні моделі, а також розроблено математичні методи теорії надійності.",
			"З 1971 по 1981 рр. кафедру очолював кандидат фіз.-мат. наук, доцент А.Т. Моторний, який керував госпдоговірної темою на замовлення Інституту економіки АН УРСР.З 1971 по 1981 рр. кафедру очолював кандидат фіз.-мат. наук, доцент А.Т. Моторний, який керував госпдоговірної темою на замовлення Інституту економіки АН УРСР.",
			"З 1976 по 1983 р. здійснювалося наукове співробітництво кафедри із заводом ім. В. А. Малишева (у вигляді робіт госпдоговірної тематики). Керівником теми був професор В. Ф. Шукайло.",
			"Більше 30 років – з 1972 по 2005 р. – на кафедрі працював кандидат фіз.-мат. наук, доцент В. В. Михайлов.",
			"Тринадцять років – з 1981 по 1994 р. – Володимир Васильович Михайлов був її завідувачем. Багато років він керував науково-дослідною роботою за темою «Розробка і вдосконалення наукового та методичного забезпечення курсу вищої математики».",
			"З 1972 р. співробітником кафедри вищої математики був кандидат техн. наук, доц. І. С. Храповицький, який брав активну участь у розробці та виданні методичних посібників для студентів заочної форми навчання.",
			"За період з 1976 по 1980 р. колектив кафедри поповнили кандидат фіз.-мат. наук, доцент С. В. Троїцький, який деякий час завідував кафедрою, кандидат фіз.-мат. наук Є. Д. Файнберг, кандидат фіз.-мат. наук, доцент Н. М. Бланк,І. К. Кириченко, доктор фіз.-мат. наук, професор О. М. Литвин, кандидат техн. наук, доцент А. Д. Кріон, кандидат фіз.-мат. наук, доцент А. І. Милославський, кандидат фіз.-мат. наук, доцент С. С. Кан, кандидат фіз.-мат. наук, доцент І. Н. Ахієзер, доценти В. О. Галета, В. П. Індикул, С. Ф. Євдокимов.",
			"У різні роки на кафедрі працювали генеральний директор корпорації «НДІ Монокристалл», доктор техн. наук Б. В. Гриньов, доктор фіз.-мат. наук В. І. Ткач, асистенти В. В. Хаджинова і О. Ф. Рофе-Бекетова – онука знаменитого архітектора О. М. Бекетова.",
			"З 1989 р. по 2006 р. кафедру вищої математики очолював доктор фіз.-мат. наук, проф. С. С. Недорезов.",
			"У 1990 р. в колектив кафедри влилися нові співробітники – О. Д. Пташний і Т. В. Ємельянова. З часом вони захистили кандидатські дисертації.",
			"У 1995 р. від кафедри вищої математики відокремилася кафедра прикладної математики на чолі з доктором фіз.-мат.наук, професором О. М. Литвином – фахівцем у галузі обчислювальної математики і математичного моделювання, учнем академіка В. Л. Рвачова, лауреатом премії ім. акад. В. М. Глушкова Національної академії наук України і стипендіатом обласної премії ім. М. В. Остроградського. О. М. Литвин автор більш ніж 450 наукових робіт. У числі перших викладачів кафедри були доценти Людмила Семенівна Лобанова, Анатолій Дмитрович Кріон, Іван Степанович Храповицький, асистенти Інна Володимирівна Крікова, Тетяна Миколаївна Нестерова та Олеся Петрівна Нечуйвітер.",
			"З перших днів роботи кафедри її керівник приділяв велику увагу кадровому поповненню професорсько-викладацького складу, зокрема, з числа випускників аспірантури. З розширенням переліку читаних на кафедрі дисциплін і збільшенням контингенту студентів значно зросло і навчальне навантаження – на кафедру прийшли працювати кандидат фіз.-мат. наук Наталія Дмитрівна Сизова, доктор техн. наук, професор Анатолій Леонідович Литвинов і кандидат техн. наук, асистент Любов Миколаївна Ушаковська.",
			"Кафедра підтримувала тісний зв'язок з установами профтехосвіти: для викладачів училищ проводилися консультації з питань методики викладання математики, кращим випускникам допомагали готуватися до вступу в УІПА.",
			"У період 2007 р. – 2009 р. на кафедрі вищої математики під керівництвом доктора фіз.-мат. наук І. К. Кириченко працювали кандидат техн. наук, доцент Ю. І. Созонов, кандидат пед. наук О. Д. Пташний, приват-доцент Т. Н. Трикова, приват-доцент Н. Ф. Бедрицька, кандидат фіз.-мат. наук Ю. А. Касаткін, кандидат фіз.-мат. наук А. М. Петров, асистенти Ю. А. Яновський, А. Ю. Шмалій. Закінчили аспірантуру І. В. Крікова, Є. Р. Пашковська, О. П. Нечуйвітер, К. В. Носов, К. Є. Бабенко, Є. І. Дробот, В. В. Міхалкін, Т. А. Баранова, Л. І. Гулик, Ю. І. Першина, С. І. Кулик, Г. В. Каргапольцева, В. А. Пасічник, І. В. Нефедова, Г. А. Чаусова і Г. В. Залужна.",
			"Колектив кафедри надавав велике значення вдосконаленню навчального процесу та його методичного забезпечення.Так, за період з 1995 р. розроблено та опубліковано навчальні посібники та методичні вказівки з курсів «Вища математика», «Теорія ймовірності і математична статистика», «Економетрія», «Математичні методи та моделі в розрахунках на ПЕОМ», «Математична статистика», «Математична теорія теплопровідності», «Операційне числення», «Математичне моделювання», «Математичне програмування», «Спеціальні розділи вищої математики», «Основи дискретної математики».",
			"Одним з найважливіших напрямків в діяльності кафедри була науково-дослідна робота. Ось лише кілька тем, над якими працював колектив в кінці 90-х – початку 2000-х рр.: нові високоефективні методи розв'язання плоскої та просторової задач комп'ютерної томографії, засновані на використанні сплайн-інтерлінації та сплайн-інтерфлетаціі функції; теоретико-практичні аспекти навчання студентів дисципліни «Математичні методи та моделі в розрахунках на ПЕОМ»; математичні моделі, чисельні методи, їх дослідження та впровадження в навчальний процес у спецкурсах кафедри.",
			"З 2005 р. кафедра вела держбюджетну науково-дослідну роботу за двома темами: «Математичне моделювання фізико-технічних систем» і «Розробка та реалізація планів безперервної математичної підготовки для студентів інженерно-педагогічних спеціальностей. Удосконалення та реалізація в навчальному процесі навчальних і комп'ютерних програм математичної підготовки студентів».",
			"У 2009 р. злиттям двох кафедр: вищої математики та прикладної математики була створена кафедра вищої та прикладної математики. Завідувачем кафедри став доктор фіз.-мат. наук, професор Олег Миколайович Литвин.",
			"У 2010 році кафедрою сумісно з Інститутом кібернетики ім. В.М. Глушкова НАН України створено Науковий міжвідомчий центр «Математичне моделювання структури неоднорідного тіла з використанням нових методів розв’язання крайових задач і методів комп’ютерної та сейсмічної томографії».На кафедрі діє наукова школа Литвина Олега Миколайович. На кафедрі вищої та прикладної математики викладаються наступні курси:/nВища математика, Математичні методи та моделі в розрахунках на ПЕОМ, Економетрія,Спеціальні розділи вищої математики, Математична теорія теплопроводності, Теорія ймовірності, Математична статистика, Дискретна математика, Математичне програмування./nУ своїй роботі викладачі використовують найсучасніші технології та постійно підвищують свій професійний рівень.",
			"За період існування кафедри було отримано 4 патенти, видано 5 монографій, 2 навчальні посібники з грифом МОН та понад 500 статей у фахових виданнях." };
	private static final String[] HISTORY_UNDER_RU = { 
			"В 1958 г. была основана кафедра высшей математики. Первым ее заведующим с 1958 по 1962 гг. был кандидат физ.-мат. наук, доцент Иван Александрович Наумов. Под его руководством были составлены первые программы по курсу высшей математики.", 
			"Приемником И.А. Наумова стал канд. физ.-мат. наук, доцент Тарас Григорьевич Иваницкий, который возглавлял кафедру с 1962 по 1964 гг. В то время на кафедре работали В.Н. Жуковская, С.Б. Веселовский и Т.С. Коваль. В 1963 году на кафедру пришла работать ассистент Т.Н. Трикова. Гордостью коллектива были такие талантливые математики: доктор физ.-мат. наук, профессор Г.И. Дринфельд, который с 1964 по 1965 гг. руководил кафедрой; доктор физ.-мат. наук, профессор Б.Я. Левин – зав. кафедрой с 1965 по 1966 гг. В разное время кафедрой заведовали В.Н. Жуковская, П.И. Белик, В.Н. Кибер, А.А. Челидзе. В период с 1968 по 1970 гг. коллектив пополнился молодыми сотрудниками, в числе которых Л.С. Лобанова, Н.Ф. Бедрицкий, Ю.И. Созонов, Е.А. Ганенко, М.А. Милитиди. В составе кафедры работали И.М. Однопозова, С.И. Пархоменко, С.И. Болиславская, А.П. Яхно, С.Г.Мильграм.", 
			"В это же время на кафедру пришел выдающийся ученый кандидат физ.-мат. наук Виктор Федорович Шукайло. Под его руководством было получено основополагающие результаты демографических исследований и построены соответствующие математические модели, а также разработаны математические методы теории надежности.", 
			"С 1971 по 1981 гг. кафедру возглавлял кандидат физ.-мат. наук, доцент А.Т. Моторный, который руководил хоздоговорной темой по заказу Института экономики АН УССР.", 
			"С 1976 по 1983 гг. осуществлялось научное сотрудничество кафедры с заводом им. В.А. Малышева (в виде работ хоздоговорной тематики). Руководителем темы был профессор В.Ф. Шукайло.", 
			"Более 30 лет – с 1972 по 2005 гг. – на кафедре работал кандидат физ.-мат. наук, доцент В.В. Михайлов.", 
			"Тринадцать лет – с 1981 по 1994 гг. - Владимир Васильевич Михайлов был ее заведующим. Много лет он руководил научно-исследовательской работой по теме «Разработка и совершенствование научного и методического обеспечения курса высшей математики».", 
			"С 1972 г. сотрудником кафедры высшей математики был кандидат техн. наук, доц. И. С. Храповицкий, который принимал активное участие в разработке и издании методических пособий для студентов заочной формы обучения.", 
			"За период с 1976 по 1980 гг. коллектив кафедры пополнили кандидат физ.-мат. наук, доцент С.В. Троицкий, который некоторое время заведовал кафедрой, кандидат физ.-мат. наук Е.Д. Файнберг, кандидат физ.-мат. наук, доцент Н.М. Бланк, И.К. Кириченко, доктор физ.-мат. наук, профессор О.Н. Литвин, кандидат техн. наук, доцент А.Д. Крион, кандидат физ.-мат. наук, доцент А.И. Милославский, кандидат физ.-мат. наук, доцент С.С. Кан, кандидат физ.-мат. наук, доцент И.Н. Ахиезер, доценты В.А. Галета, В.П. Индикул, С.Ф. Евдокимов.", 
			"В разные годы на кафедре работали генеральный директор корпорации «НИИ Монокристалл», доктор техн. наук Б.В. Гринев, доктор физ.-мат. наук В.И. Ткач, ассистенты В.В. Хаджинова и А.Ф. Рофе-Бекетова – внучка знаменитого архитектора А.Н. Бекетова.", 
			"С 1989 г. по 2006 гг. кафедру высшей математики возглавлял доктор физ.-мат. наук, проф. С. С. Недорезов.", 
			"В 1990г. в коллектив кафедры влились новые сотрудники – О.Д. Пташный и Т.В. Емельянова. Со временем они защитили кандидатские диссертации.", 
			"В 1995г. от кафедры высшей математики отделилась кафедра прикладной математики во главе с доктором физ.-мат.наук, профессором О.Н. Литвином – специалистом в области вычислительной математики и математического моделирования, учеником академика В.Л. Рвачева, лауреатом премии им. акад. В.М. Глушкова Национальной академии наук Украины и стипендиатом областной премии им. М.В. Остроградского. О.Н. Литвин автор более 450 научных работ. В числе первых преподавателей кафедры были доценты Людмила Семеновна Лобанова, Анатолий Дмитриевич Крион, Иван Степанович Храповицкий, ассистенты Инна Владимировна Крикова, Татьяна Николаевна Нестерова и Олеся Петровна Нечуйвитер.", 
			"С первых дней работы кафедры ее руководитель уделял большое внимание кадровому пополнению профессорско-преподавательского состава, в частности, из числа выпускников аспирантуры. С расширением перечня читаемых на кафедре дисциплин и увеличением контингента студентов значительно возросло и учебная нагрузка – на кафедру пришли работать кандидат физ.-мат. наук Наталья Дмитриевна Сизова, доктор техн. наук, профессор Анатолий Леонидович Литвинов и кандидат техн. наук, ассистент Любовь Николаевна Ушаковского.", 
			"Кафедра поддерживала тесную связь с учреждениями профтехобразования: для преподавателей училищ проводились консультации по вопросам методики преподавания математики, лучшим выпускникам помогали готовиться к поступлению в УИПА.", 
			"В период с 2007 по 2009 гг. на кафедре высшей математики под руководством доктора физ.-мат. наук И.К. Кириченко работали кандидат техн. наук, доцент Ю.И. Созонов, кандидат пед. наук О.Д. Пташный, приват-доцент Т.Н. Трикова, приват-доцент Н.Ф. Бедрицкий, кандидат физ.-мат. наук Ю.А. Касаткин, кандидат физ.-мат. наук А.М. Петров, ассистенты Ю.А. Яновский, А.Ю. Шмалий. Закончили аспирантуру И.В. Крикова, Е.Р. Пашковская, О.П. Нечуйвитер, К.В. Носов, К.Е. Бабенко, Е.И. Дробот, В.В. Михалкин, Т.А. Баранова, Л.И. Гулик, Ю.И. Першина, С.И. Кулик, В. Каргапольцев, В.А. Пасечник, И.В. Нефедова, Г.А. Чаусова и Г.В. Залужна.",
			"Коллектив кафедры придавал большое значение совершенствованию учебного процесса и его методического обеспечения. Так, за период с 1995 г. разработаны и опубликованы учебные пособия и методические указания по курсам «Высшая математика», «Теория вероятности и математическая статистика», «Эконометрика», «Математические методы и модели в расчетах на ПЭВМ», «Математическая статистика» , «Математическая теория теплопроводности», «Операционное исчисление», «Математическое моделирование», «Математическое программирование», «Специальные разделы высшей математики», «Основы дискретной математики».", 
			"Одним из важнейших направлений в деятельности кафедры была научно-исследовательская работа. Вот несколько тем, над которыми работал коллектив в конце 90-х – начале 2000-х гг.: новые высокоэффективные методы решения плоской и пространственной задач компьютерной томографии, основанные на использовании сплайн-интерлинации и сплайн-интерфлетации функции; теоретико-практические аспекты обучения студентов дисциплине «Математические методы и модели в расчетах на ЭВМ»; математические модели, численные методы, их исследования и внедрение в учебный процесс в спецкурсах кафедры.", 
			"С 2005 г. кафедра вела госбюджетную научно-исследовательскую работу по двум темам: «Математическое моделирование физико-технических систем» и «Разработка и реализация планов непрерывной математической подготовки для студентов инженерно-педагогических специальностей. Совершенствование и реализация в учебном процессе учебных и компьютерных программ математической подготовки студентов».", 
			"В 2009 г. слиянием двух кафедр: высшей математики и прикладной математики была создана кафедра высшей и прикладной математики. Заведующим кафедрой стал доктор физ.-мат. наук, профессор Олег Николаевич Литвин.", 
			"В 2010 году кафедрой совместно с Институтом кибернетики им. В.М. Глушкова НАН Украины создан Научный межведомственный центр «Математическое моделирование структуры неоднородного тела с использованием новых методов решения краевых задач и методов компьютерной и сейсмической томографии». На кафедре действует научная школа Литвина Олега Николаевича. На кафедре высшей и прикладной математики преподаются следующие курсы: Высшая математика; Математические методы и модели в расчетах на ПЭВМ; эконометрия; Специальные разделы высшей математики; Математическая теория теплопроводности; Теория вероятности; Математическая статистика; Дискретная математика, Математическое программирование. В своей работе преподаватели используют современные технологии и постоянно повышают свой профессиональный уровень.",
			"За период существования кафедры было получено 4 патента, издано 5 монографий, 2 учебных пособия с грифом МОН и более 500 статей в профессиональных изданиях."};
	private static final String[] HISTORY_UNDER_EN = { 
			"In 1958 the Department of Higher Mathematics was founded. Its first head from 1958 to 1962 was a Candidate of Physical and Mathematical Sciences, Associate Professor Ivan Alexandrovich Naumov. Under his leadership, the first programs on the course of higher mathematics were compiled.", 
			"The receiver of I.A. Naumov became a Candidate of Physical and Mathematical Sciences, Associate Professor Taras Grigorievich Ivanitsky, who headed the department from 1962 to 1964. At that time at department working V.N. Zhukovskaya, S.B. Veselovsky and T.S. Koval. In 1963 the assistant T.N. Trikova was starting working at department. The pride of the collective was such talented mathematicians: Doctor of Physical and Mathematical Sciences, Professor G.I. Drinfeld, who from 1964 to 1965 headed the department; Doctor of Physical and Mathematical Sciences, Professor B.Ya. Levin - headed  from 1965 to 1966. At different times the department was headed by VN. Zhukovskaya, P.I. Belik, V.N. Cyber, A.A. Chelidze. In the period from 1968 to 1970 the staff was replenished with young employees, among them LS. Lobanova, N.F. Bedritsky, Yu.I. Sozonov, E.A. Ganenko, M.A. Militidi. I.M. Odnopozova, S.I. Parkhomenko, S.I. Bolislavskaya, A.P. Yakhno, S.G. Milgram was worked in the structure of the department.", 
			"At the same time an outstanding scientist, Candidate of Physical and Mathematical Sciences, Victor Fedorovich Shukaylo, came to the department. Under his leadership, the basic results of demographic studies were obtained and the corresponding mathematical models were constructed, and mathematical methods of reliability theory were developed.", 
			"From 1971 to 1981 the department was headed by the Candidate of Physical and Mathematical Sciences, Associate Professor A.T. Motorniy, which led the contractual subject on the order of the Institute of Economics of the Academy of Sciences of the Ukrainian SSR.", 
			"From 1976 to 1983 the scientific cooperation of the department with the plant was carried out V.A. Malysheva (in the form of works of economic contracts). The head of the topic was Professor V.F. Shukailo.", 
			"More than 30 years - from 1972 to 2005 - the Candidate of Physical and Mathematical Sciences, Associate Professor, V.V. Mikhailov worked at the department.", 
			"Thirteen years - from 1981 to 1994 - Vladimir Vasilyevich Mikhailov was its head. For many years he led research work on the theme «Development and improvement of scientific and methodological support of the course of higher mathematics».", 
			"Since 1972, the employee of the department of higher mathematics was a Candidate of Technical Sciences, Associate Professor I.S. Khrapovitsky, who took an active part in the development and publication of methodological manuals for students in correspondence courses.", 
			"Between 1976 and 1980 the staff of the department was joined by the Candidate of Physical and Mathematical Sciences, Associate Professor S.V. Troitsky, who was in charge of the chair for some time, Candidate of Physical and Mathematical Sciences E.D. Feinberg, Candidate of Physical and Mathematical Sciences, Associate Professor N.B.Blank, I.K. Kirichenko, Doctor of Physical and Mathematical Sciences, Professor О.N. Lytvyn, Candidate of Technical Sciences, Associate Professor A.D. Krion, Candidate of Physical and Mathematical Sciences, Associate Professor А.I. Miloslavsky, Candidate of Physical and Mathematical Sciences, Associate Professor S.S. Kan, Candidate of Physical and Mathematical Sciences, Associate Professor I.N. Akhiezer, Associate Professors V.A. Galeta, V.P. Indikul, S.F. Evdokimov.", 
			"In different years at the department was worked the general director of the corporation «NII Monocrystal», Doctor of Technical Sciences B.V. Grinev, Doctor of Physical and Mathematical Sciences V.V. Tkach, Assistants V.V. Khadzhinov and A.F. Rofe-Beketova is the granddaughter of the famous architect A.N. Beketov.", 
			"From 1989 to 2006 Department of Higher Mathematics was headed by Doctor of Physical and Mathematical Sciences, Professor S.S. Nedorezov.", 
			"In 1990 the staff of the department was joined by new employees – O.D. Ptashny and T.V. Emelyanova. Over time, they defended their Ph.D. theses.", 
			"In 1995 From the Department of Higher Mathematics the Department of Applied Mathematics, headed by the Doctor of Physical and Mathematical Science, Professor O.N. Lytvyn - an expert in the field of computational mathematics and mathematical modeling, a student of Academician V.L. Rvachev, the winner of the award. Acad. V.M. Glushkova National Academy of Sciences of Ukraine and a grant holder of the regional award them. M.V. Ostrogradsky. O.N. Lytvyn is the author of more than 450 scientific papers. Among the first teachers of the department were Associate Professors Lyudmila Semenovna Lobanova, Anatoly Dmitrievich Krion, Ivan Stepanovich Khrapovitsky, assistants Inna Vladimirovna Cricova, Tatyana Nikolaevna Nesterova and Olesya Petrovna Nechuiviter.", 
			"From the very first days of the department's work, its head paid great attention to the staff recruiting of the faculty, in particular, from the graduates of the postgraduate school. With the expansion of the list of disciplines read at the department and the increase in the contingent of students, the teaching load has also significantly increased - a Candidate of Technacal Sciences Natalia Dmitrievna Sizova, Doctor of Technical Sciences, Professor Anatoly Leonidovich Litvinov and Candidate of Technacal Sciences, Assistant Lyubov Nikolaevna Ushakovsky.", 
			"The chair maintained close contact with vocational education institutions: for teachers of colleges, consultations were held on the methods of teaching mathematics, the best graduates helped prepare for entering the UIPA.", 
			"In the period from 2007 to 2009 at the Department of Higher Mathematics under the supervision of a Doctor of Physical and Mathematical Sciences I.K. Kirichenko worked with Candidate of Technacal Sciences, Associate Professor Yu.I. Sozonov, Candidate Pedagogical Of Sciences O.D. Ptashny, privat-docent T.N. Trikova, privat-docent N.F. Bedritsky, Candidate of Physical and Mathematical Sciences Yu.A. Kasatkin, Candidate of Physical and Mathematical Sciences A.M. Petrov, assistant Yu.A. Yanovsky, A.Yu. Shmaliy. Graduated from the post-graduate course of I.V. Cricova, E.R. Pashkovskaya, О.P. Nechuiviter, K.V. Nosov, K.E. Babenko, E.I. Drobot, V.V. Michalkin, T.A. Baranova, L.I. Gulik, Yu.I. Pershyna, S.I. Kulik, V. Kargapoltsev, V.A. Pasechnik, I.V. Nefedova, G.A. Chausova and G.V. Zaluzna.",
			"The staff of the department attached great importance to the improvement of the educational process and its methodological support. Thus, since 1995, textbooks and methodological guidelines on courses «Higher Mathematics», «Probability Theory and Mathematical Statistics», «Econometrics», «Mathematical Methods and Models in Calculations on a PC», «Mathematical Statistics» have been developed and published , «Mathematical theory of heat conductivity», «Operational calculus», «Mathematical modeling», «Mathematical programming», «Special sections of higher mathematics», «Fundamentals of discrete mathematics».", 
			"One of the most important directions in the activity of the department was research work. Here are a few topics on which the team worked in the late 90s – early 2000s: new highly effective methods for solving the planar and spatial problems of computed tomography based on the use of spline-interlination and spline-interflation of functions; Theoretical and practical aspects of teaching students the discipline «Mathematical methods and models in computer calculations»; Mathematical models, numerical methods, their research and introduction into the educational process in the special courses of the department.", 
			"Since 2005, the department has conducted state budget research work on two topics: «Mathematical modeling of physical and technical systems» and «Development and implementation of plans for continuous mathematical preparation for students of engineering and pedagogical specialties. Improvement and realization in the educational process of educational and computer programs of students' mathematical preparation».", 
			"In 2009, the Department of Higher and Applied Mathematics was created by merging the two departments: Higher Mathematics and Applied Mathematics. The head of the department was Doctor of Physical and Mathematical Sciences, Professor Oleg Nikolaevich Lytvyn.", 
			"In 2010, the department jointly with the Institute of Cybernetics named after. V.M. Glushkov National Academy of Sciences of Ukraine created the Scientific Interdepartmental Center «Mathematical modeling of the structure of an inhomogeneous body with the use of new methods for solving boundary-value problems and methods of computer and seismic tomography». At the department there is the scientific school of Lytvyn Oleg Nikolaevich. The following courses are taught at the Department of Higher and Applied Mathematics: Higher Mathematics; Mathematical methods and models in calculations on a PC; econometrics; Special sections of higher mathematics; Mathematical theory of heat conductivity; Probability theory; Math statistics; Discrete mathematics, Mathematical programming. In their work, teachers use modern technologies and constantly improve their professional level.",
			"For the period of the department's existence, 4 patents were received, 5 monographs, 2 manuals with the stamp of the MES and more than 500 articles in professional publications were published."};

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("history");
		} else
			getHistoryRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getHistoryRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(setHistoryInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private static String setHistoryInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getHistoryHull(Template.TITLE_DEPARTMENT_HISTORY_RU, HISTORY_PRE_RU, HISTORY_UNDER_RU);
			break;
		case "en":
			res = getHistoryHull(Template.TITLE_DEPARTMENT_HISTORY_EN, HISTORY_PRE_EN, HISTORY_UNDER_EN);
			break;
		default:
			res = getHistoryHull(Template.TITLE_DEPARTMENT_HISTORY_UA, HISTORY_PRE_UA, HISTORY_UNDER_UA);
		}
		return res;
	}

	private static String getHistoryHull(String header, String[] pre, String[] under) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return "<h2 class='header'>" + header + "</h2>" 
				+ "<div class='Information'>" 
				+ "<p style='cursor:pointer; font-family:franklin gothic medium;font-size: 18px' onClick='expandit(this)'><strong id='Name_F'>1958-1971▼</strong></p>"
				+ "<span style='display:none' style=&{head};>" 
				+ "<p class='under'><p align='justify' class='Name'>" + under[0] + "</p></p>"
				+ "<p class='under'><p align='justify' class='Name'>" + under[1] + "</p></p>" 
				+ "<p class='under'><p align='justify' class='Name'>" + under[2] + "</p></p>" 
				+ "</span>"
				+ "<p style='cursor:pointer; font-family:franklin gothic medium;font-size: 18px' onClick='expandit(this)'><strong id='Name_F'>1971-1995▼</strong></p>"
				+ "<span style='display:none' style=&{head};>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[3] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[4] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[5] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[6] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[7] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[8] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[9] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[10] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[11] + "</p></p>" 
				+ "</span>"
				+ "<p style='cursor:pointer; font-family:franklin gothic medium;font-size: 18px' onClick='expandit(this)'><strong id='Name_F'>1995-" + dateFormat.format(date) +"▼</strong></p>"
				+ "<span style='display:none' style=&{head};>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[12] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[13] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[14] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[15] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[16] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[17] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[18] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[19] + "</p></p>" 
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[20] + "</p></p>"
				+ "<p class= 'under'><p align='justify' class='Name'>" + under[21] + "</p></p>"
				+ "</span>"
				+ "<p class='pre'><p align='justify' class='Name'>" + pre[0] 
				+ "<a href='" + LINK + "'>" + pre[1] + "</a></p></p>"
				+ "</div>" 
				+ "<script language='JavaScript1.2' src='script/expand.js'></script>";
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_DEPARTMENT_HISTORY_RU;
			break;
		case "en":
			res = Template.TITLE_DEPARTMENT_HISTORY_EN;
			break;
		default:
			res = Template.TITLE_DEPARTMENT_HISTORY_UA;
		}
		return res;
	}
}
