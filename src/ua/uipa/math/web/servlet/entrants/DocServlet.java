package ua.uipa.math.web.servlet.entrants;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class DocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String[] INFO_UA = {
			"Прийом заяв і документів, фахові вступні випробування, що проводить УІПА, конкурсний відбір і зарахування на навчання вступників на основі ступенів бакалавра, магістра, ОКР спеціаліста (а також осіб, які не менше одного року здобувають ступінь магістра та виконують у повному обсязі індивідуальний навчальний план) за ступенем магістра проводиться в такі строки:",
			"Під час подання заяви в паперовій формі вступник пред'являє особисто оригінали:",
			"документа, що посвідчує особу;",
			"військового квитка або посвідчення про приписку – для військовозобов’язаних;",
			"документа державного зразка (оригінал) про раніше здобутий освітній (освітньо-кваліфікаційний) рівень, на основі якого здійснюється вступ, і додатка до нього.",
			"До заяви, поданої в паперовій формі, вступник додає:",
			"копію документа, що посвідчує особу;", 
			"копію документа державного зразка про раніше здобутий освітній (освітньо-кваліфікаційний) рівень, на основі якого здійснюється вступ, і копію додатка до нього;", 
			"копію довідки про присвоєння ідентифікаційного номера;",
			"чотири кольорові фотокартки розміром 3×4 см.", 
			"У заяві в паперовій формі передбачається згода вступника на обробку персональних даних. Оригінали документів при участі в конкурсі подаються вступником лише один раз.",
			"Усі копії документів засвідчуються за оригіналами Приймальною комісією. Копії документа, що посвідчує особу, військового квитка (посвідчення про приписку) не підлягають засвідченню."
	};
	private static String[] INFO_RU = {
			"Прием заявлений и документов, вступительные экзамены, которые проводит УИПА, конкурсный отбор и зачисление на обучение поступающих на основе степеней бакалавра, магистра, ОКР специалиста (а также лиц, не менее одного года получают степень магистра и выполняют в полном объеме индивидуальный учебный план ) по степени магистра проводится в следующие сроки:",
			"При подаче заявления в бумажной форме поступающий предъявляет лично оригиналы:",
			"документа, удостоверяющего личность;",
			"военного билета или приписного – для военнообязанных;",
			"документа государственного образца (оригинал) о ранее полученном образовательном (образовательно-квалификационном) уровне, на основе которого осуществляется поступление, и приложение к нему;",
			"К заявлению, поданному в бумажной форме, поступающий добавляет:",
			"копию документа, удостоверяющего личность;",
			"копию документа государственного образца о ранее полученном образовательном (образовательно-квалификационном) уровне, на основе которого осуществляется поступление, и копию приложения к нему;",
			"копию справки о присвоении идентификационного номера;",
			"четыре цветные фотографии размером 3 × 4 см.",
			"В заявлении в бумажной форме предполагается согласие поступающего на обработку персональных данных. Оригиналы документов при участии в конкурсе подаются поступающим только один раз.",
			"Все копии документов заверяются по оригиналам Приемной комиссией. Копии документа, удостоверяющего личность, военного билета (приписного) не подлежат заверению."
	};
	private static String[] INFO_EN = {
			"Acceptance of applications and documents, entrance examinations conducted by the UIPA, competitive selection and enrollment for admission on the basis of bachelor's, master's, or OCR degrees (as well as individuals, receive a master's degree and complete an individual curriculum for at least one year) on Master's degree is held in the following terms:",
			"During application in paper form original entrant presents personally:",
			"identification document;",
			"military ticket;",
			"government-issued document (original) previously acquired education (educational qualification) level, through which the introduction and annex;",
			"The application filed in paper form, the entrant adds:",
			"a copy of identity;",
			"a copy of the document state of previously acquired education (educational qualification) level, through which the entry, copy and annex;",
			"a copy of the certificate of identification numbers;",
			"four color photographs measuring 3 × 4 cm.",
			"The statement in paper form agreement provides for an applicant to the processing of personal data. The original documents for the contest entrant submitted only once.",
			"All certified copies of the original selection committee. Copies of the document identification, military card (certificate of origin) are not subject to certification."
	};
	
	private static String[][] TABLE_UA = {
			{"Етапи вступної кампанії",
				"Денна форма навчання",
				"Заочна форма навчання"},
			{"Початок прийому заяв і документів",
				"26 червня 2017 року",
				"26 червня 2017 року"},
			{"Закінчення прийому заяв і документів",
				"24 липня 2017 року",
				"24 липня 2017 року"},
			{"Строки проведення УІПА вступних (в т.ч. додаткових) випробувань",
				"24 липня – 2 серпня 2017 року",
				"24 липня – 2 серпня 2017 року"},
			{"Термін оприлюднення рейтингового списку вступників",
				"5 серпня 2017 року",
				"5 серпня 2017 року"},
			{"Терміни зарахування вступників",
				"за державним замовленням – не пізніше 10 серпня 2017 року; за кошти фізичних та юридичних осіб – не пізніше 25 серпня 2017 року",
				"за державним замовленням – не пізніше 10 серпня 2017 року; за кошти фізичних та юридичних осіб – не пізніше 25 серпня 2017 року"}};
	
	private static String[][] TABLE_RU = {
			{"Этапы вступительной кампании",
				"Дневная форма обучения",
				"Заочная форма обучения"},
			{"Начало приема заявлений и документов",
				"26 июня 2017",
				"26 июня 2017"},
			{"Окончание приема заявлений и документов",
				"24 июля 2017",
				"24 июля 2017"},
			{"Сроки проведения УИПА вступительных (в т.ч. дополнительных) экзаменов",
				"24 июля – 2 августа 2017",
				"24 июля – 2 августа 2017"},
			{"Срок обнародования рейтингового списка поступающих",
				"5 августа 2017",
				"5 августа 2017"},
			{"Сроки зачисления поступающих",
				"по государственному заказу – не позднее 10 августа 2017; за средства физических и юридических лиц – не позднее 25 августа 2017",
				"по государственному заказу – не позднее 10 августа 2017; за средства физических и юридических лиц – не позднее 25 августа 2017"}};
	
	private static String[][] TABLE_EN = {
			{"The stages of the campaign",
				"Daytime form education",
				"Correspondence from education"},
			{"Start receiving applications and documents",
				"June 26, 2017",
				"June 26, 2017"},
			{"Deadline of applications and documents",
				"July 24, 2017",
				"July 24, 2017"},
			{"Terms of the UIPA for introductory (including additional) exams",
				"July 24 – August 2, 2017",
				"July 24 – August 2, 2017"},
			{"The term disclosure rating list of entrants",
				"August 5, 2017",
				"August 5, 2017"},
			{"Terms of enrollment applicants",
				"by the state order – no later than 10 August 2017; at the expense of individuals and legal entities – no later than August 25, 2017",
				"by the state order – no later than 10 August 2017; at the expense of individuals and legal entities – no later than August 25, 2017"}};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("entrants_docs");
		} else
			getDocRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getDocRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(RuleInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String RuleInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = DocsHull(INFO_RU, TABLE_RU);
			break;
		case "en":
			res = DocsHull(INFO_EN, TABLE_EN);
			break;
		default:
			res = DocsHull(INFO_UA, TABLE_UA);
		}
		return res;
	}

	private String DocsHull(String[] info, String[][] table) {
		String res = "";
		res += "<div class='Information'>" + 
				"<p align='justify' class='Name'>" + info[0] + "</p>" + 
				"<table border='1' class='doctable'>" +
				"<tr><th><p class='Name'>" + table[0][0] + "</p></th>" +
				"<th><p class='Name'>" + table[0][1] + "</p></th>" +
				"<th><p class='Name'>" + table[0][2] + "</p></th></tr>";
		for(int i = 1; i<=5; i++)
			res+="<tr><td><p class='Name'>" + table[i][0] + "</p></td>" +
				"<td><p align='justify' class='Name'>" + table[i][1] + "</p></td>" +
				"<td><p align='justify' class='Name'>" + table[i][2] + "</p></td></tr>";
		res += "</table><p class='Name'>" + info[1] + "</p>" +
				"<ul><li><div align='justify' class='Name'>" + info[2] + "</div></li>" +
				"<li><div align='justify' class='Name'>" + info[3] + "</div></li>" +
				"<li><div align='justify' class='Name'>" + info[4] + "</div></li></ul>" +
				"<p class='Name'>" + info[5] + "</p>" + 
				"<ul><li><div align='justify' class='Name'>" + info[6] + "</div></li>" +
				"<li><div align='justify' class='Name'>" + info[7] + "</div></li>" +
				"<li><div align='justify' class='Name'>" + info[8] + "</div></li>" +
				"<li><div align='justify' class='Name'>" + info[9] + "</div></li></ul>" +
				"<p align='justify' class='Name'>" + info[10] + "</p>" + 
				"<p align='justify' class='Name'>" + info[11] + "</p>";
		res += "</div>";
		return res;
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_ENTRANTS_DOCS_RU;
			break;
		case "en":
			res = Template.TITLE_ENTRANTS_DOCS_EN;
			break;
		default:
			res = Template.TITLE_ENTRANTS_DOCS_UA;
		}
		return res;
	}

}
