# TrabalhoFinal

# 1 Objetivo
O sistema de passagens operará em desktop, com uma GUI feita em Java Swing. Ele deve atender os compradores e as empresas de ônibus. Para tal, teremos os seguintes modos de acesso:
Cliente: Possui login e tem a capacidade de verificar passagens, comprar passagens, imprimir passagens e cancelar passagens.
Admin: Também possui login e tem a capacidade de registrar novas viagens, atualizar viagens existentes, excluir viagens e verificar os dados pessoais dos clientes.

Ambos os modos de acesso são classes que herdam da classe pai Usuário, que contém seus dados pessoais:

O usuário precisa de login e senha de acesso. Caso o usuário não conste no banco de dados, deve fazer um registro. As informações dentro da classe Usuário são: nome, sobrenome, data de nascimento, CPF e residência (sendo esta uma classe com CEP, cidade, rua, bairro e número).

# 2 Funcionalidade
- Permitir o cadastro de novos usuários. Os clientes podem fazer o próprio registro, já os admins precisam ser registrados por outro admin.
- O cliente deve escolher o dia, local de partida e destino. A seguir, verá as viagens disponíveis no dia, cada uma com seu horário e preço. 
- Ao selecionar a viagem, o cliente vê os bancos vagos. Selecionando um banco, ele parte para o pagamento. Após pagar, pode imprimir a passagem.

- O sistema deve permitir o registro de viagem por admins, informando a data de início,  a data de fim, os terminais por onde passa e o horário estimado para passar entre cada um. 
- Cada viagem precisa dos dados do ônibus: sua identificação, vagas livres e ocupadas. 
- O preço das viagens é dado para o cliente com base no terminal em que ele vai entrar e no terminal em que vai sair.

- O Admin pode ver todos os dados das viagens, dos clientes e dos ônibus que estão no sistema.

# UML
![uml](https://github.com/user-attachments/assets/50a0cba3-d8bf-4c64-ae13-2bc5abbe2054)


# Banco de Dados
![imagemDB](https://github.com/user-attachments/assets/0df6c7ea-c432-4cfa-b3b0-bbe9fc43a478)
