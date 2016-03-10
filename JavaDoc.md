# Introdução #

Breve explicação de como utilizar o JavaDoc, para garantir que a sua aplicação possua uma documentação padronizada.




## ''Tags'' Javadoc ##

| **Tag**      |        **Descrição** |
|:-------------|:---------------------|
| @author      |	Nome do desenvolvedor|
|@deprecated   |Marca o método como deprecated. Algumas IDEs exibirão um alerta de compilação se o método for chamado.|
|@exception    | 	Documenta uma exceção lançada por um método — veja também @throws.|
|@param        |	Define um parâmetro do método. Requerido para cada parâmetro.|
|@return 	     |Documenta o valor de retorno. Essa tag não deve ser usada para construtores ou métodos definidos com o tipo de retorno void.|
|@see          |	Documenta uma associação a outro método ou classe.|
|@since        |	Documenta quando o método foi adicionado a a classe.|
|@throws       |	Documenta uma exceção lançada por um método. É um sinônimo para a @exception introduzida no Javadoc 1.2.|
|@version      |	Exibe o número da versão de uma classe ou um método.|


## Exemplo ##
```
    /**
     * Salva solicitação no banco de dados
     * @param  criteria criteria
     * @param  func objeto funcionario
     * @param  tipoDservico tipo de serviço solicitado
     * @param  especialidade especialidade do serviço solicitado
     * @param  patrimonio patrimônio do equipamento
     * @param  descricao descrição da solicitação
     * @param  dataExecucao data de execução da solicitação
     * @param  itinerario intinerário
     * @param  justificativa justificativa da solicitação
     * @return número da solicitação criada
     * @throws BusinessLogicException erro de logica de negocio
     * @throws PersistenceException erro de persistencia
     */
    Integer salvarRequisicao ( Funcionario func, String tipoDservico,String  especialidade, String patrimonio,String  descricao,
        Date dataExecucao, String itinerario, String justificativa  )
        throws BusinessLogicException, PersistenceException;
}
```