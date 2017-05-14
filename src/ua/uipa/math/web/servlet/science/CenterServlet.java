package ua.uipa.math.web.servlet.science;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;
import ua.uipa.math.db.DBManager;
import ua.uipa.math.db.entity.Science;
import ua.uipa.math.exception.DBException;

public class CenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String[] INFO_UA = {
			"Науковий центр «Нові методи математичного моделювання структури неоднорідних тіл»",
			"Українська інженерно-педагогічна академія в особі ректора О.Е. Коваленко з одного боку та Інститут кібернетики ім. В.М. Глушкова НАН України в особі директора інституту І.В. Сергієнка з іншого боку з метою координації науково-дослідної діяльності створили науковий міжвідомчий центр «Нові методи математичного моделювання структури неоднорідних тіл»",
			"Коваленко Олена Едуардівна",
			"Координатор",
			"Ректор Української інженерно-педагогічної академії, завідувач кафедри педагогіки та методики професійного навчання, професор, доктор педагогічних наук.",
			"Член Ради з освіти, культури і мистецтва при Державній акредитаційній комісії Міністерства освіти і науки України, член Ради Північно-східного наукового центру науково-координаційного органу національної Академії наук і Міністерства освіти і науки України в Харківській, Полтавській і Сумській областях, дійсний член (академік) Міжнародної академії проблем Людини в авіації і космонавтиці. Почесний академік товариства академіків Харківського національного технічного університету сільського господарства імені Петра Василенка.",
			"Сергієнко Іван Васильович",
			"Координатор",
			"Радник Президії НАН України, директор Інституту кібернетики ім. В.М.Глушкова, генеральний директор Кібернетичного центру НАН України, Академік НАН України, професор, доктор фіз.- мат. наук.",
			"Голова Національного Комітету України з інформатики, голова Наукової ради НАН України “Інтелектуальні інформаційні технології”, голова Наукової ради з проблеми “Кібернетика” при Президії НАН України. Заслужений діяч науки і техніки України, Лауреат Державних премій в галузі науки і техніки України та СРСР.",
			"Литвин Олег Миколайович",
			"Науковий керівник",
			"Професор, доктор фізико–математичних наук, завідувач кафедри вищої та прикладної математики Української інженерно-педагогічної академії, почесний професор Бердянського державного педагогічного університету.",
			"Лауреат премії ім. В.М.Глушкова Президії НАН України, лауреат Державної премії в галузі науки і техніки України, лауреат Харківської обласної премії М.В. Остроградського, нагороджений нагрудними знаками «За наукові досягнення», «Відмінник освіти України». Отримує Державну стипендію, призначену для видатних вчених України.",
			"Наукові результати:",
			"Основні напрямки роботи▼",
			"Науково-дослідні теми▼",
			"Публікації▼",
			"Монографії▼",
			"Патенти▼"};
	private static final String[] INFO_RU = {
			"Научный центр «Новые методы математического моделирования структуры неоднородных тел»",
			"Украинская инженерно-педагогическая академия в лице ректора О.Е. Коваленко с одной стороны и Институт кибернетики им. В.М. Глушкова НАН Украины в лице директора института И.В. Сергиенко с другой стороны с целью координации научно-исследовательской деятельности создали научный межведомственный центр «Новые методы математического моделирования структуры неоднородных тел» ",
			"Коваленко Елена Эдуардовна",
			"Координатор",
			"Ректор Украинской инженерно-педагогической академии, заведующий кафедрой педагогики и методики профессионального обучения, профессор, доктор педагогических наук.",
			"Член Совета по образованию, культуре и искусству при Государственной аккредитационной комиссии Министерства образования и науки Украины, член Совета Северо-Восточного научного центра научно-координационного органа национальной Академии наук и Министерства образования и науки Украины в Харьковской, Полтавской и Сумской областях, действительный член ( академик) Международной академии проблем человека в авиации и космонавтике. Почетный академик общества академиков Харьковского национального технического университета сельского хозяйства имени Петра Василенко. ",
			"Сергиенко Иван Васильевич",
			"Координатор",
			"Советник Президиума НАН Украины, директор Института кибернетики им. В.М. Глушкова, генеральный директор Кибернетического центра НАН Украины, академик НАН Украины, профессор, доктор физ. мат. Наук.",
			"Председатель Национального Комитета Украины по информатике, председатель Научного совета НАН Украины «Интеллектуальные информационные технологии», председатель Научного совета по проблеме «Кибернетика» при Президиуме НАН Украины. Заслуженный деятель науки и техники Украины, Лауреат Государственных премий в области науки и техники Украины и СССР.",
			"Литвин Олег Николаевич",
			"Научный руководитель",
			"Профессор, доктор физико-математических наук, заведующий кафедрой высшей и прикладной математики Украинской инженерно-педагогической академии, почетный профессор Бердянского государственного педагогического университета.",
			"Лауреат премии им. В.М. Глушкова Президиума НАН Украины, лауреат Государственной премии в области науки и техники Украины, лауреат Харьковской областной премии М.В. Остроградского, награжден нагрудными знаками« За научные достижения »,« Отличник образования Украины ». Получает государственную стипендию, предназначенную для выдающихся ученых Украины.",
			"Научные результаты:",
			"Основные направления работы ▼",
			"Научно-исследовательские темы ▼",
			"Публикации ▼",
			"Монографии ▼",
			"Патенты ▼"
	};
	private static final String[] INFO_EN = {
			"Scientific Center «»New methods of mathematical modeling of heterogeneous structures bodies»",
			"Ukrainian Engineering and Pedagogics Academy, represented by Rector Kovalenko O.E. and the Institute of Cybernetics, NAS Ukraine represented by the Director of the Institute Sergienko I.V. on the other hand to coordinate research activities have established research center «New methods of mathematical modeling of heterogeneous structures bodies»",
			"Kovalenko Olena E.",
			"Coordinator",
			"Rector of the Ukrainian Engineering and Pedagogical Academy, Chair of pedagogy and methodology training, Professor, Doctor of Education.",
			"Member of the Board of Education, Culture and Art of the State Accreditation Commission of the Ministry of Education and Science of Ukraine, member of the Northeastern Research Center research and coordinating body of the national Academy of Sciences and the Ministry of Education and Science of Ukraine in Kharkiv, Poltava and Sumy regions, Member ( Academician) of the International Academy of Human problems in aviation and aerospace. Honorary Academician Society academics Kharkov national technical University of agriculture Petro Vasilenko.",
			"Sergienko Ivan V.",
			"Coordinator",
			"Advisor of NAS of Ukraine, Director of the Institute of Cybernetics. Glushkov, CEO of Cyber ​​Center of Ukraine, Academy of Sciences of Ukraine, Professor, Doctor Phys. and Math. Sciences.",
			"Chairman of the National Committee of Ukraine for science, Chairman of the Scientific Council of NAS of Ukraine «Intelligent Information Technologies», Head of the Scientific Council on the problem «Cybernetics» of the Presidium of NAS of Ukraine. Honored Scientist of Ukraine, laureate of State Prize in Science and Technology of Ukraine and the USSR.",
			"Lytvyn Oleg M.",
			"Supervisor",
			"Professor, Doctor of Physical and Mathematical Sciences, Head of the Department of Applied Mathematics and Ukrainian Engineering and Pedagogical Academy, Professor Emeritus Berdyansk State Pedagogical University.",
			"Winner of the Glushkov prize of NAS of Ukraine, State Prize in Science and Technology of Ukraine, winner of the Kharkiv Regional Award MV Ostrogradski awarded the medal «For scientific achievements, excellence in education». Gets state grants designated for outstanding scientists of Ukraine.",
			"Scientific results",
			"Areas of work ▼",
			"Research topics ▼",
			"Publications ▼",
			"Monographs ▼",
			"Patents ▼"
	};
	private static final String RECTOR = "/math/images/kovalenko.jpg";
	private static final String ACADEM = "/math/images/sergienko.jpg";
	private static final String PROF = "/math/images/Sklad_Kaf/LitvinON.jpg";
	private static final String PAPER = "paper";
	private static final String MONOGRAPHY = "monography";
	private static final String PATENT = "patent";
	private static final String AREA = "area";
	private static final String THEME = "theme";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("center");
		} else
			getCenterRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getCenterRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(getCenterInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_SCIENCE_CENTER_RU;
			break;
		case "en":
			res = Template.TITLE_SCIENCE_CENTER_EN;
			break;
		default:
			res = Template.TITLE_SCIENCE_CENTER_UA;
		}
		return res;
	}

	private String getCenterInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getCenterHull(request, INFO_RU);
			break;
		case "en":
			res = getCenterHull(request, INFO_EN);
			break;
		default:
			res = getCenterHull(request, INFO_UA);
		}
		return res;
	}

	private String getCenterHull(HttpServletRequest request, String[] info) {
		String res = "";
		try {
			List<Science> papers = DBManager.getInstance().getCenterItemsByType(PAPER, request);
			List<Science> monographs = DBManager.getInstance().getCenterItemsByType(MONOGRAPHY, request);
			List<Science> patents = DBManager.getInstance().getCenterItemsByType(PATENT, request);
			List<Science> areas = DBManager.getInstance().getCenterItemsByType(AREA, request);
			List<Science> themes = DBManager.getInstance().getCenterItemsByType(THEME, request);
			res += "<script type='text/javascript' src='script/expand.js'></script>" 
					+ "<div class='Information'>"
					+ "<h2>" + info[0] + "</h2>"
					+ "<p align='justify' class='Name'>" + info[1] + "</p>"
					+ "<table border='1' class='doctable'>"
					+ "<tr><td>"
					+ "<img class='leftimg' src='" + RECTOR + "'>"
					+ "<p align='center' class='Name'><strong>" + info[2] + "</strong></p>"
					+ "<p align='justify' class='Name'>" + info[3] + "</p>"
					+ "<p align='justify' class='Name'>" + info[4] + "</p>"
					+ "<p align='justify' class='Name'>" + info[5] + "</p></td></tr>"
					+ "<tr><td>"
					+ "<img class='leftimg' src='" + ACADEM + "'>"
					+ "<p align='center' class='Name'><strong>" + info[6] + "</strong></p>"
					+ "<p align='justify' class='Name'>" + info[7] + "</p>"
					+ "<p align='justify' class='Name'>" + info[8] + "</p>"
					+ "<p align='justify' class='Name'>" + info[9] + "</p></td></tr>"
					+ "<tr><td>"
					+ "<img class='leftimg' src='" + PROF + "'>"
					+ "<p align='center' class='Name'><strong>" + info[10] + "</strong></p>"
					+ "<p align='justify' class='Name'>" + info[11] + "</p>"
					+ "<p align='justify' class='Name'>" + info[12] + "</p>"
					+ "<p align='justify' class='Name'>" + info[13] + "</p></td></tr></table>"
					+ "<p align='center' class='Name'>" + info[14] + "</p>"
					+ "<p align='center' style='cursor:pointer' onClick='expandit(this)'>" + info[15] + "</p>"
					+ "<span style='display:none' style=&{head};>" 
					+ "<ol class='first'>";
			for (int i = 0; i < areas.size(); i++)
				res += "<li>" + (i + 1) + ". " + areas.get(i).getTitle() + "</li>";
			res += "</ol></span>"
					+ "<p align='center' style='cursor:pointer' onClick='expandit(this)'>" + info[16] + "</p>"
					+ "<span style='display:none' style=&{head};>" 
					+ "<ol class='first'>";
			for (int i = 0; i < themes.size(); i++)
				res += "<li>" + (i + 1) + ". " + themes.get(i).getTitle() + "</li>";
			res += "</ol></span>"
					+ "<p align='center' style='cursor:pointer' onClick='expandit(this)'>" + info[17] + "</p>"
					+ "<span style='display:none' style=&{head};>" 
					+ "<ol class='first'>";
			for (int i = 0; i < papers.size(); i++)
				res += "<li>" + (i + 1) + ". " + papers.get(i).getTitle() + "</li>";
			res += "</ol></span>"
					+ "<p align='center' style='cursor:pointer' onClick='expandit(this)'>" + info[18] + "</p>"
					+ "<span style='display:none' style=&{head};>" 
					+ "<ol class='first'>";
			for (int i = 0; i < monographs.size(); i++)
				res += "<li>" + (i + 1) + ". " + monographs.get(i).getTitle() + "</li>";
			res += "</ol></span>"
					+ "<p align='center' style='cursor:pointer' onClick='expandit(this)'>" + info[19] + "</p>"
					+ "<span style='display:none' style=&{head};>" 
					+ "<ol class='first'>";
			for (int i = 0; i < patents.size(); i++)
				res += "<li>" + (i + 1) + ". " + patents.get(i).getTitle() + "</li>";
			res += "</ol></span></div>";
		} catch (DBException e) {
			e.printStackTrace();
		}
		return res;
	}
}
