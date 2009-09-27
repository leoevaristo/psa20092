function checa(){
	
	
	
	if (confirm('Você deseja excluir esse cliente?')) {
			return true;
		} 
	
	
	return false;
	
	

	}



function tipoPessoa(){
	
	var tipo = document.getElementById("form1:campo0");
	var panelRg = document.getElementById("form1:panelRg");
	var panelCpf = document.getElementById("form1:panelCpf");
	var panelCnpj = document.getElementById("form1:panelCnpj");
	var panelSexo = document.getElementById("form1:panelSexo");
	
	if(tipo.value == "J"){
		
		panelRg.style.display = "none";
		panelCpf.style.display = "none";	
		panelSexo.style.display = "none";
		if(panelCnpj.style.display == "none"){
			panelCnpj.style.display = "block";
		}
		
	}else if(tipo.value == "F"){
		
		panelCnpj.style.display = "none";
		
		if((panelRg.style.display == "none") && (panelCpf.style.display == "none") && (panelSexo.style.display == "none")){
			
			panelRg.style.display = "block";
			panelCpf.style.display = "block";
			panelSexo.style.display = "block";
		}
		
		
	}else if(tipo.value == ""){
		
		panelRg.style.display = "none";
		panelCpf.style.display = "none";
		panelCnpj.style.display = "none";
		panelSexo.style.display = "none";
	}
	
function validaCampo()
{
	var nome = document.getElementById("form1:campoNome");
	
	if (nome.value == "")
	{
		alert("nulo!!");
	}
   	
	
}

	
	
	
}