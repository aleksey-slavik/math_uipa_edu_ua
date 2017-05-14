package ua.uipa.math.web.servlet.entrants;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class SpecialityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static String BRANCH_UA = "Галузь знань: ";
	private final static String BRANCH_RU = "Область знаний: ";
	private final static String BRANCH_EN = "Branch of Knowledge: ";
	private final static String SPECIALTY_UA = "Спеціальність: ";
	private final static String SPECIALTY_RU = "Специальность: ";
	private final static String SPECIALTY_EN = "Speciality: ";
	private final static String SPECIALIZATION_UA = "Спеціалізація: ";
	private final static String SPECIALIZATION_RU = "Специализация: ";
	private final static String SPECIALIZATION_EN = "Specialization: ";
	private final static String QUALIFICATION_UA = "Кваліфікація: ";
	private final static String QUALIFICATION_RU = "Квалификация: ";
	private final static String QUALIFICATION_EN = "Qualification: ";
	private final static String[] INFO_UA = {
			"11 Математика та Статистика",
			"113 Прикладна Математика",
			"Магістр",
			"Прикладна математика – галузь науки, яка знаходиться на стику математики і комп’ютерних наук. Тому фахівець цієї предметної області – це спеціаліст, здатний вирішити прикладне завдання, ефективно використовуючи існуючі математичні моделі і методи, а у разі потреби, розробити нові.", 
			"Важливим напрямом діяльності кафедри є:",
			"За цими напрямами підготовку магістрів будуть здійснювати 4 доктори фізико-математичних наук.",
			"Основні місця роботи випускників спеціальності \"Прикладна математика\":",
			"Після закінчення магістратури за спеціальністю \"Прикладна математика\" є можливість вступу до аспірантури за цією ж спеціальністю.",
	};
	private final static String[] INFO_RU = {
			"11 Математика и Статистика",
			"113 Прикладная Математика",
			"Магистр",
			"Прикладная математика - область науки, которая находится на стыке математики и компьютерных наук. Поэтому специалист этой предметной области - это специалист, способный решить прикладную задачу, эффективно используя существующие математические модели и методы, а в случае необходимости, разработать новые.",
			"Важным направлением деятельности кафедры являются:",
			"По этим направлениям подготовки магистров будут осуществлять 4 доктора физико-математических наук.",
			"Основные места работы выпускников специальности \"Прикладная математика\":",
			"После окончания магистратуры по специальности \"Прикладная математика\" является возможность поступления в аспирантуру по этой же специальности.",
	};
	private final static String[] INFO_EN = {
			"11 Mathematics and Statistics",
			"113 Applied Mathematics",
			"Master degree",
			"Applied Mathematics - a branch of science which is located at the intersection of mathematics and computer science. Therefore, this subject area specialist - a specialist able to solve practical tasks efficiently using existing mathematical models and methods, and, if necessary, develop new ones.",
			"An important activity of the department are:",
			"For these directions of preparation of Masters will perform 4 Doctor of Phisical and Mathimatical Sciences.",
			"The main job of graduates of the specialty \"Applied Mathematics\":",
			"After graduation, specialty \"Applied Mathematics\" is the possibility of admission to graduate school for the same specialty."
	};
	private final static String[] SKILL_UA = {
			"наближення функцій багатьох змінних;",
			"оптимальний метод скінченних елементів, у якому вузлові параметри, базисні функції та координати вузлів елементів знаходяться з умови мінімуму функціоналу;",
			"метод розв’язання крайових задач з частинними похідними, у якому крайова задача зводиться до системи лінійних інтегро-диференціальних рівнянь або системи нелінійних інтегро-диференціальних рівнянь, яка забезпечує більш високу точність розв’язання крайових задач;",
			"розробка теорії дивідіріальних та мультигральних числень, які є узагальненнями класичного диференціального та інтегрального числення;",
			"розробка теорії наближення функцій багатьох змінних за допомогою операторів інтерлінації, інтерфлетації та мішаної апроксимації;",
			"розробка теорії інтерполяції та апроксимації диференціальних операторів за допомогою інших диференціальних операторів;",
			"розробка нових високоефективних методів розв'язання задач плоскої та просторової комп’ютерної томографії;",
			"розробка нових високоефективних методів цифрової обробки багатовимірних сигналів;",
			"розробка оптимальних кубатурних формул для наближеного обчислення інтегралів від швидко осцилюючих функцій багатьох змінних;",
			"розробка математичних моделей методами інтерлінації функцій в розвідці корисних копалин і конструюванні  поверхонь."
	};
	private final static String[] SKILL_RU = {
			"приближение функций многих переменных;",
			"оптимальный метод конечных элементов, в котором узловые параметры, базисные функции и координаты узлов элементов находятся из условия минимума функционала;",
			"метод решения краевых задач в частных производных, в котором краевая задача сводится к системе линейных интегро-дифференциальных уравнений или системы нелинейных интегро-дифференциальных уравнений, которая обеспечивает более высокую точность решения краевых задач;",
			"разработка теории дивидириальних и мультигральних исчислений, которые являются обобщениями классического дифференциального и интегрального исчисления;",
			"разработка теории приближения функций многих переменных с помощью операторов интерлинации, интерфлетации и смешанной аппроксимации;",
			"разработка теории интерполяции и аппроксимации дифференциальных операторов с помощью других дифференциальных операторов;",
			"разработка новых высокоэффективных методов решения задач плоской и пространственной компьютерной томографии;",
			"разработка новых высокоэффективных методов цифровой обработки многомерных сигналов;",
			"разработка оптимальных кубатурных формул для приближенного вычисления интегралов от быстро осциллирующих функций многих переменных;",
			"разработка математических моделей методами интерлинации функций в разведке полезных ископаемых и конструировании поверхностей."
	};
	private final static String[] SKILL_EN = {
			"аpproximation of functions of several variables;",
			"optimal finite element method in which the node parameters, basic functions and coordinates node elements are provided with minimum functionality;",
			"method of solving boundary value problems of partial, in which the boundary problem is reduced to a system of linear integral-differential equations or systems of nonlinear integral-differential equations, which provides higher accuracy of boundary problems;",
			"development divirate theory and calculus of multygrals which are generalizations of the classical differential and integral calculus;",
			"development of approximation theory of functions of several variables with operators interlination, interflatation and mixed approximation;",
			"development of the theory of interpolation and approximation of differential operators using other differential operators;",
			"development of new highly efficient methods for solving problems of plane and spatial CT;",
			"development of new highly efficient methods of digital processing of multidimensional signals;",
			"development of optimal cubature formulas for approximate calculation of integrals of rapidly oscillating functions of several variables;",
			"development of mathematical models based on methods  of interlination of functions in mineral exploration and design surfaces."
	};
	private final static String[] ACTIVITY_UA = {
			"банківські установи;",
			"відділ обробки інформації підприємств різних форм власності;",
			"фірми, які займаються розробкою прикладного програмного забезпечення;",
			"наукові інститути і установи;",
			"вищі навчальні заклади."
	};
	private final static String[] ACTIVITY_RU = {
			"банковские учреждения;",
			"отдел обработки информации предприятий различных форм собственности;",
			"фирмы, которые занимаются разработкой прикладного программного обеспечения;",
			"научные институты и учреждения;",
			"высшие учебные заведения."
	};
	private final static String[] ACTIVITY_EN = {
			"banks;",
			"Department of Information Processing enterprises of different ownerships;",
			"the company involved in the development application software;",
			"research institutes and establishments;",
			"universities."
	};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if(lang == null){
			session.setAttribute("lang", "en");
			response.sendRedirect("entrants_speciality");
		}
		else
			getData(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void getData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(getSpecialityInfo(request));
		out.print(Template.setFooter());
		out.close();
	}
	
	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_ENTRANTS_SPESIALITY_RU;
			break;
		case "en":
			res = Template.TITLE_ENTRANTS_SPESIALITY_EN;
			break;
		default:
			res = Template.TITLE_ENTRANTS_SPESIALITY_UA;
		}
		return res;
	}
	
	private String getSpecialityInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getSpecialityHull(
					BRANCH_RU,
					SPECIALTY_RU,
					SPECIALIZATION_RU, 
					QUALIFICATION_RU, 
					INFO_RU,
					SKILL_RU,
					ACTIVITY_RU);
			break;
		case "en":
			res = getSpecialityHull(
					BRANCH_EN,
					SPECIALTY_EN,
					SPECIALIZATION_EN, 
					QUALIFICATION_EN, 
					INFO_EN,
					SKILL_EN,
					ACTIVITY_EN);
			break;
		default:
			res = getSpecialityHull(
					BRANCH_UA,
					SPECIALTY_UA,
					SPECIALIZATION_UA, 
					QUALIFICATION_UA, 
					INFO_UA,
					SKILL_UA,
					ACTIVITY_UA);
		}
		return res;
	}
	
	private static String getSpecialityHull(String branch, String speciality, 
			String specialization, String qualification, String[] info, String[] skill, String[] act) {
		String res = "<div class='Information'>" + 
				"<p align='justify' class='Name'><strong>" + branch + "</strong>" + info[0] + "</p>" + 
				"<p align='justify' class='Name'><strong>" + speciality + "</strong>" + info[1] + "</p>" +
				"<p align='justify' class='Name'><strong>" + qualification + "</strong>" + info[2] + "</p>" +
				"<p align='justify' class='Name'>" + info[3] + "</p>" +
				"<p align='justify' class='Name'><strong>" + info[4] + "</strong></p>" +
				"<ul>";
		for(String s : skill)
			res += "<li><div align='justify' class='Name'>" + s + "</div></li>";
		res += "</ul><p align='justify' class='Name'>" + info[5] + "</p>" +
				"<p align='justify' class='Name'><strong>" + info[6] + "</strong></p><ul>";
		for(String s : act)
			res += "<li><div align='justify' class='Name'>" + s + "</div></li>";
		res += "</ul><p align='justify' class='Name'><strong>" + info[7] + "</strong></p></div>";
		return res;
	}
}
