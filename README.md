# ArtificialCareApi 

# Endpoints

## Usuário:
- `GET` URL+/webapi/usuario (GET ALL CLIENTES)
- `GET` URL+/webapi/usuario/{email} (GET BY EMAIL)
- `PUT` URL+/webapi/usuario/{email}
```
Body: {
	"nome" : "Nome do Usuario",
	"email" : "nome@email.com",
	"senha" : "senha123",
	"peso" : 74,
	"altura" : 1.82,
	"nascimento" : "2005-07-01"
      }
```
- `DELETE` URL+/webapi/usuario/{email}
- `POST` URL+/webapi/usuario/
```
    Body: {
	"nome" : "Nome do Usuario",
	"email" : "nome@email.com",
	"senha" : "senha123",
	"peso" : 80,
	"altura" : 1.92,
	"nascimento" : "2005-07-01"
      }
``` 
## Ações:
- `GET` URL+/webapi/acoes (GET ALL Dicas)
- `GET` URL+/webapi/acoes/{id} (GET BY ID)
- `GET` URL+/webapi/acoes/{emailCliente}/{qtd} (GET BY Email/Qtd) </br>
*A quantidade de açoes que quer obter. caso peça 4 e tenha 2: retorna 2*
- `PUT` URL+/webapi/acoes/{id}
```
Body: {
	"data": "2023-01-01Z",
	"descricao": "Ação 1",
	"duracao": 32,
	"score": 1
}
```
- `DELETE` URL+/webapi/acoes/{id}
- `POST` URL+/webapi/acoes
```
Body: {
	"data": "2023-01-01Z",
	"descricao": "Ação 1",
	"duracao": 32,
	"score": 1,
	"usuario": {
		"email": {email}
	}   
 }
 ```
## Dicas:
- `GET` URL+/webapi/dicas (GET ALL CLIENTES)
- `GET` URL+/webapi/webapi/dicas/{id} (GET BY ID)
- `PUT` URL+/webapi/dicas/{id}
```
Body: {
	"categoria" : "Bom",
	"texto" : "Continue nesse ritmo!"
}
```
- `DELETE` URL+/webapi/dicas/{id}
- `POST` URL+/webapi/dicas
```
Body: {
	"categoria" : "Bom",
	"texto" : "Continue assim!"
}
```

## Login: 
- `POST` URL+/webapi/login
```
Body: {
 	 "email" : "nome@email.com",
	"senha" : "senha123"
}
```
## Diagrama de classes
```mermaid
classDiagram
direction BT
class Acoes {
  + Acoes(Long, Usuario, int, String, double, Date) 
  + Acoes() 
  + Acoes(int, String, double, Date) 
  + Acoes(Usuario, int, String, double, Date) 
  - Long id
  - Date data
  - String descricao
  - Usuario usuario
  - int score
  - double duracao
   String descricao
   Date data
   Long id
   int score
   Usuario usuario
   double duracao
}
class AcoesDao {
  + AcoesDao() 
  + update(Long, Acoes) void
  + findById(Long) Acoes?
  + create(Acoes) void
  + findByEmailUsuario(String, int) List~Acoes~
  + delete(Long) void
  + findAll() List~Acoes~
}
class AcoesResource {
  + AcoesResource() 
  + getByIdUsuario(String, int) Response
  + delete(Long) Response
  + update(Long, Acoes) Response
  + getById(Long) Response
  + create(Acoes) Response
   Response all
}
class AcoesService {
  + AcoesService() 
  + create(Acoes) void
  + findAll() List~Acoes~
  + update(Long, Acoes) Mensagem
  + delete(Long) Mensagem
  + findById(Long) Acoes
  + findByEmailUsuario(String, int) List~Acoes~
}
class ConnectionFactory {
  + ConnectionFactory() 
   Connection? connection
}
class CorsFilter {
  + CorsFilter() 
  + filter(ContainerRequestContext, ContainerResponseContext) void
}
class Dicas {
  + Dicas() 
  + Dicas(String, String) 
  + Dicas(Long, String, String) 
  - String categoria
  - Long id
  - String texto
   Long id
   String texto
   String categoria
}
class DicasDao {
  + DicasDao() 
  + findAll() List~Dicas~
  + create(Dicas) void
  + delete(Long) void
  + find(Long) Dicas?
  + update(Long, Dicas) void
}
class DicasResource {
  + DicasResource() 
  + create(Dicas) Response
  + delete(Long) Response
  + update(Long, Dicas) Response
  + getOne(Long) Response
   Response all
}
class DicasService {
  + DicasService() 
  + findAll() List~Dicas~
  + update(Long, Dicas) Mensagem
  + delete(Long) Mensagem
  + find(Long) Dicas
  + create(Dicas) Mensagem
}
class LoginResource {
  + LoginResource() 
  + login(Usuario) Response
}
class LoginService {
  + LoginService() 
  - validar(Usuario) Mensagem
  + login(Usuario) Mensagem
}
class Mensagem {
  + Mensagem() 
  + Mensagem(String, boolean) 
  - String resposta
  - boolean sucesso
   String resposta
   boolean sucesso
}
class Usuario {
  + Usuario(String) 
  + Usuario(String, String, String, Date, double, Double) 
  + Usuario() 
  + Usuario(String, String) 
  + Usuario(Long, String, String, String, Date, double, Double) 
  - String nome
  - String email
  - Double altura
  - Date nascimento
  - Long id
  - String senha
  - double peso
   double peso
   String nome
   Long id
   String email
   String senha
   Double altura
   Date nascimento
}
class UsuarioDao {
  + UsuarioDao() 
  + create(Usuario) void
  + findAll() List~Usuario~
  + delete(String) void
  + update(String, Usuario) void
  + find(String) Usuario?
}
class UsuarioResource {
  + UsuarioResource() 
  + getall() Response
  + get(String) Response
  + create(Usuario) Response
  + delete(String) Response
  + update(String, Usuario) Response
}
class UsuarioService {
  + UsuarioService() 
  + find(String) Usuario
  + create(Usuario) Mensagem
  - validar(Usuario) Mensagem
  + findAll() List~Usuario~
  + delete(String) void
  + update(String, Usuario) Mensagem
}

Acoes "1" *--> "usuario 1" Usuario 
AcoesDao  ..>  Acoes : «create»
AcoesDao  ..>  ConnectionFactory 
AcoesDao  ..>  Usuario : «create»
AcoesResource  ..>  Acoes 
AcoesResource  ..>  AcoesService 
AcoesResource  ..>  Mensagem : «create»
AcoesResource  ..>  Usuario 
AcoesResource  ..>  UsuarioService 
AcoesService  ..>  Acoes 
AcoesService  ..>  AcoesDao 
AcoesService  ..>  Mensagem : «create»
DicasDao  ..>  ConnectionFactory 
DicasDao  ..>  Dicas : «create»
DicasResource  ..>  Dicas 
DicasResource  ..>  DicasService 
DicasResource  ..>  Mensagem : «create»
DicasService  ..>  Dicas 
DicasService  ..>  DicasDao 
DicasService  ..>  Mensagem : «create»
LoginResource  ..>  LoginService 
LoginResource  ..>  Mensagem 
LoginResource  ..>  Usuario 
LoginService  ..>  LoginService 
LoginService  ..>  Mensagem : «create»
LoginService  ..>  Usuario 
LoginService  ..>  UsuarioService 
UsuarioDao  ..>  ConnectionFactory 
UsuarioDao  ..>  Usuario : «create»
UsuarioResource  ..>  Mensagem : «create»
UsuarioResource  ..>  Usuario 
UsuarioResource  ..>  UsuarioService 
UsuarioService  ..>  Mensagem : «create»
UsuarioService  ..>  Usuario 
UsuarioService  ..>  UsuarioDao 
UsuarioService  ..>  UsuarioService 

```
