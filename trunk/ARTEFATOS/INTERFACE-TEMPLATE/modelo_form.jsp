<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<f:view>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/layout.css" rel="stylesheet" type="text/css"
		media="screen" />
	<title><h:outputText value="SIAIC - Modelo Form Padrão" /></title>
	</head>
	<body>
		 <h:form id="formGroup01">
			 <fieldset>
		     	<legend>
				 <h:outputText value="Grupo01" styleClass="lab" /> 
			    </legend>
				<h:panelGrid columns="2" border="1">
				<h:outputLabel for="textoIn" value="texto:" />
				<h:inputText value="" id="textoIn" />
				<h:outputLabel for="opcoes" value="Opções:" />
				<h:selectOneMenu styleClass="selec" id="opcoes" value="">
					<f:selectItem itemValue="opção 1" />
					<f:selectItem itemValue="opção 2" />
					<f:selectItem itemValue="opção 3" />
				</h:selectOneMenu>
				<h:outputLabel for="radioGroup" value="Radio:" />
				<h:selectOneRadio id="radioGroup" styleClass="rad" value="">
					<f:selectItem itemValue="Radio01" itemLabel="Radio01" />
					<f:selectItem itemValue="Radio02" itemLabel="Radio02" />
					<f:selectItem itemValue="Radio03" itemLabel="Radio03" />
				</h:selectOneRadio>
				<h:outputLabel for="checaBox" styleClass="rad" value="Checkboxes" />
				<h:selectManyCheckbox id="checaBox" value="" layout="lineDirection">
					<f:selectItem itemValue="Check01" />
					<f:selectItem itemValue="Check02" />
					<f:selectItem itemValue="Check03" />
				</h:selectManyCheckbox>
				<h:outputLabel for="blocoTexto" value="Bloco de Texto" />
				<h:inputTextarea id="blocoTexto" cols="10" rows="5"></h:inputTextarea>
			</h:panelGrid>
			</fieldset>
			 <fieldset>
		     	<legend>
				 <h:outputText value="Grupo02" styleClass="lab" /> 
			    </legend>
				<h:panelGrid columns="2" border="1">
				<h:outputLabel for="textoIn2" value="texto:" />
				<h:inputText value="" id="textoIn2" />
				<h:outputLabel for="opcoes2" value="Opções:" />
				<h:selectOneMenu styleClass="selec" id="opcoes2" value="">
					<f:selectItem itemValue="opção 1" />
					<f:selectItem itemValue="opção 2" />
					<f:selectItem itemValue="opção 3" />
				</h:selectOneMenu>
				<h:outputLabel for="radioGroup2" value="Radio:" />
				<h:selectOneRadio id="radioGroup2" styleClass="rad" value="">
					<f:selectItem itemValue="Radio01" itemLabel="Radio01" />
					<f:selectItem itemValue="Radio02" itemLabel="Radio02" />
					<f:selectItem itemValue="Radio03" itemLabel="Radio03" />
				</h:selectOneRadio>
				<h:outputLabel for="checaBox2" styleClass="rad" value="Checkboxes" />
				<h:selectManyCheckbox id="checaBox2" value="" layout="lineDirection">
					<f:selectItem itemValue="Check01" />
					<f:selectItem itemValue="Check02" />
					<f:selectItem itemValue="Check03" />
				</h:selectManyCheckbox>
				<h:outputLabel for="blocoTexto2" value="Bloco de Texto" />
				<h:inputTextarea id="blocoTexto2" cols="10" rows="5"></h:inputTextarea>
			</h:panelGrid>
			</fieldset>
			<h:panelGrid columns="2">
				<h:commandButton value="Enviar Dados" action="enviar"  />
				<h:commandButton value="Limpar Formulário" action="limpar" type="reset" />			
			</h:panelGrid>
		</h:form>	
	</body>
</f:view>
</html>