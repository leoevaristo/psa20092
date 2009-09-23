function checa(){
	
	
	
	if (confirm('Você tem certeza que deseja excluir esse cliente?')) {return true }; return false;
	
	

	}



function tipoPessoa(){
	
	var tipo = document.getElementById("form1:campo0");
	var panelRg = document.getElementById("form1:panelRg");
	var panelCpf = document.getElementById("form1:panelCpf");
	var panelCnpj = document.getElementById("form1:panelCnpj");
	
	if(tipo.value == "J"){
		
		panelRg.style.visibility = "hidden";
		panelCpf.style.visibility = "hidden";
		
		if(panelCnpj.style.visibility = "hidden"){
			panelCnpj.style.visibility = "";
		}
		
	}else	if(tipo.value == "F"){
		
		panelCnpj.style.visibility = "hidden";
		
		if((panelRg.style.visibility = "hidden") && (panelCpf.style.visibility = "hidden")){
			panelRg.style.visibility = "";
			panelCpf.style.visibility = "";
		}
		
	}else if(tipo.value == ""){
		
		panelRg.style.visibility = "";
		panelCpf.style.visibility = "";
		panelCnpj.style.visibility = "";
	}
	
	

}