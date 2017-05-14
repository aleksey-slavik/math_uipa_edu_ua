package ua.uipa.math.web.servlet.postgrad;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class DocServlet extends HttpServlet {

	private static final long serialVersionUID = -9191704305218267048L;
	private static String[] INFO_UA = {
			"Згідно із ",
			"правилами прийому до аспірантури та докторантури",
			" вступники до аспірантури подають на ім'я ректора УІПА такі документи:",
			"При вступі до докторантури, крім того подаються:",
			"Усі копії документів засвідчуються за оригіналами приймальною комісією або в установленому законодавством порядку."
	};
	private static String[] INFO_RU = {
			"В соответствии с ",
			"правилами приема в аспирантуру и докторантуру",
			" поступающие в аспирантуру подают на имя ректора УИПА следующие документы:",
			"При поступлении в докторантуру, кроме того подаются:",
			"Все копии документов заверяются по оригиналам приемной комиссией или в установленном законодательством порядке."
	};
	private static String[] INFO_EN = {
			"According to ",
			"the rules of admission to postgraduate and doctoral",
			" applicants to graduate school submit the rector UIPA following documents:",
			"On admission to the doctorate also submit:",
			"All certified copies of the original selection committee or the established order."
	};
	private static String[] DOCS_UA = {
			"заяву на ім’я ректора;",
			"копія паспорта;", 
			"копія диплома про закінчення вищого навчального закладу;",
			"довідка про присвоєння ідентифікаційного коду;",
			"фото 3х4 – 2шт.;",
			"список опублікованих наукових праць і винаходів. Вступники, які не мають опублікованих наукових праць, подають наукові доповіді (реферати) з обраної ними наукової спеціальності;",
			"посвідчення про складання кандидатських іспитів (за наявності складених кандидатських іспитів);", 
			"відгук потенційного наукового керівника про співбесіду;",
			"копія трудової книжки (за наявності);",
			"особовий листок з обліку кадрів (за наявності);",
			"автобіографія;",
			"довідка з місця роботи з зазначенням середньомісячної заробітної плати (за наявності)."
	};
	private static String[] DOCS_RU = {
			"заявление на имя ректора;",
			"копия паспорта;",
			"копия диплома об окончании высшего учебного заведения;",
			"справка о присвоении идентификационного кода;",
			"фото 3х4 - 2шт .;",
			"список опубликованных научных работ и изобретений. Поступающие, не имеющие опубликованных научных работ, представляют научные доклады (рефераты) по избранной ими научной специальности;",
			"удостоверение о сдаче кандидатских экзаменов (при наличии сданных кандидатских экзаменов)",
			"отзыв потенциального научного руководителя о собеседовании;",
			"копия трудовой книжки (при наличии);",
			"личный листок по учету кадров (при наличии);",
			"автобиография;",
			"справка с места работы с указанием среднемесячной заработной платы (при наличии)."
	};
	
	private static String[] DOCS_EN = {
			"statement addressed to the Rector;",
			"copy of passport;",
			"copy of the diploma of graduation;",
			"certificate of identification code;",
			"3x4 photo - 2 pcs .;",
			"list published scientific works and inventions. Applicants who do not have scientific publications to submit scientific reports (reports) in their chosen scientific specialty;",
			"certificate of candidate examinations (if stacked candidate examinations);",
			"review the potential supervisor of the interview;",
			"copy of a work book (if available);",
			"personal data sheet frames (if available);",
			"autobiography;",
			"certificate of employment indicating the average wage (if available)."
	};
	
	private static String[] DOC_DOCS_UA = {
			"розгорнутий план дисертації на здобуття наукового ступеня доктора наук;",
			"письмову характеристику наукової діяльності вступника, складену доктором наук, який є штатним науково-педагогічним або науковим працівником академії, із згодою бути науковим консультантом;",
			"копію диплома доктора філософії або кандидата наук."
	};
	
	private static String[] DOC_DOCS_RU = {
			"развернутый план диссертации на соискание ученой степени доктора наук;",
			"письменное характеристику научной деятельности поступающего, составленную доктором наук, который является штатным научно-педагогическим или научным работником академии, с согласия быть научным консультантом;",
			"копию диплома доктора философии или кандидата наук."
	};
	private static String[] DOC_DOCS_EN = {
			"a comprehensive plan of dissertation for the degree of Doctor of Science;",
			"a written description of research activities applicant, compiled doctor, who is a tenured teaching or scientific researcher of Academy of consent to be scientific consultant;",
			"copy of PhD or Candidate of Phisics and Mathematics Sciences degree."
	};
	private static String RULE_LINK = "https://drive.google.com/file/d/0B-9AhczpgmCIX01obEFEZ3BkVGs/view?usp=sharing";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("postgrad_docs");
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
			res = DocsHull(INFO_RU, DOCS_RU, DOC_DOCS_RU);
			break;
		case "en":
			res = DocsHull(INFO_EN, DOCS_EN, DOC_DOCS_EN);
			break;
		default:
			res = DocsHull(INFO_UA, DOCS_UA, DOC_DOCS_UA);
		}
		return res;
	}

	private String DocsHull(String[] info, String[] docs, String[] add_docs) {
		String res = "";
		res += "<div class='Information'>" + 
				"<p align='justify' class='Name'>" + info[0] + 
				"<a href='" + RULE_LINK + "' target='_blank'>" + info[1] + "</a>" + info[2] + "</p><ul>";
		for(String doc : docs)
			res += "<li><div class='Name'>" + doc + "</div></li>";
		res += "</ul><p align='justify' class='Name'>" + info[3] + "</p><ul>";
		for(String doc : add_docs)
			res += "<li><div class='Name'>" + doc + "</div></li>";
		res += "</ul><p align='justify' class='Name'>" + info[4] + "</p>";
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
