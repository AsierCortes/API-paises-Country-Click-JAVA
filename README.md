APIS Utilizadas:
- Rest Countries  -> Información sobre paises
- ExchangeRate Api  -> Cambio de monedas

Idea principal: La aplicación va a ser como un asistente que te va ayudar a buscar un pais donde viajar.

Funcionalidades:
    - 1. Buscar por nombre el pais que deseas viajar. Y pedir al usuario que datos quieres saber de dicho país
        1.1 Ver que paises hay alrededor
        1.2 Ver horas de diferencia, es decir 3h de dif, y la hora exacta en ese momento (Ej: entre españa y argentina)
        1.3 Antes de viajar te informa de la moneda que hay en ese pais y te permite hacer cambio de divisas
        1.4 Ver monumenots











- Filtar por pais:
      -> Buscar por nombre el pais, además permitir al usuario elegir que datos quiere ver del país (fields = name, capital, language etc).
      -> Guardar en un caché (MAP) url y objeto pais
      Filtros existentes:
      Se puede:
          - Pedir toda la información
          - Seleccionar campos de las cuales deseas información

    Esto lo hace la API UNICAMENTE (Casi todo). Con los ENDSPOINTS
        - 1. Buscar información de un país por nombre
        - 2. Buscar varios paises a la vez (a través de codes)
        - 3. Buscar paises por lenguaje.
        - 4. Buscar paises por región.
        - 5. Buscar paises por moneda
        - 6. Buscar paises con independencia
        - 7. Buscar paises por subregiones

  Aportaciones nosotros:
      - 
      - Ver paises con más población -> Ver el que más personas tiene y el que menos.
      - Ver paises con más PIB
      - Ver paises por Areas -> Ver el que más area tiene y el que menos.
      - 
      
    
    
  
      URL:
      https://restcountries.com/v3.1/alpha?codes=es,fr,it&fields=name,currencies,language  -> Varios paises e informaciones que tu quieras
      https://restcountries.com/v3.1/currency/cop
      
