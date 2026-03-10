# ✈️ Asistente de Viajes Virtual

¡Bienvenido al repositorio del **Asistente de Viajes**! 🌍

Este proyecto es una aplicación de consola desarrollada en Java que actúa como un asistente virtual e interactivo para la planificación de viajes. Su objetivo principal es centralizar y simplificar la investigación de tu próximo destino, evitando que tengas que buscar información dispersa en diferentes sitios web. Desde datos demográficos y geográficos, hasta herramientas financieras y un motor de búsqueda de puntos de interés locales, esta aplicación te ofrece todo lo necesario para organizar tu aventura desde la terminal.

---

## ✨ Funcionalidades Principales

Nuestra aplicación ofrece una experiencia fluida paso a paso con las siguientes capacidades:

* **Radiografía del Destino:** Introduce el nombre de un país y obtén un resumen instantáneo con datos vitales: capital (tu punto de aterrizaje), idioma, huso horario, región y una estimación de la actividad del lugar basada en su volumen de población.
* **Oficina Virtual de Cambio:** Una calculadora de divisas integrada. Introduce tu moneda local, la moneda de destino y tu presupuesto para obtener el valor convertido al instante y planificar tus gastos.
* **Explorador de la Capital:** Descubre puntos de interés en la capital del país. Busca en un directorio categorizado que incluye tiendas, transporte, servicios médicos de emergencia y lugares de entretenimiento (zoos, museos, teatros). Tú controlas el radio de búsqueda y la cantidad de resultados.
* **Gestión de Favoritos e Historial:** Guarda los países que más te gusten en una lista de favoritos y consulta el historial completo de tus búsquedas durante toda la sesión para comparar destinos.
* **Persistencia de la Planificación:** Al finalizar la sesión, puedes exportar y guardar todo tu historial, lugares buscados y conversiones a un archivo local mediante serialización de objetos, asegurando que tu itinerario quede guardado de forma segura.

---

## 🛠️ APIs Utilizadas

El núcleo de la aplicación se basa en la integración de tres servicios externos que nutren de datos reales al programa:

1.  **[REST Countries API](https://restcountries.com/):** Extrae información exhaustiva del país (coordenadas de la capital, moneda oficial, idiomas, población, etc.). Es la base que da contexto al resto de funcionalidades.
2.  **[ExchangeRate-API](https://www.exchangerate-api.com/):** Se encarga de procesar el cálculo y la conversión de divisas en base a tasas de cambio actualizadas, proporcionando una herramienta financiera precisa.
3.  **[Geoapify API](https://www.geoapify.com/):** Utilizada para el motor de búsqueda espacial. Localiza lugares específicos (hospitales, restaurantes, aeropuertos) mediante el uso de coordenadas de latitud y longitud dentro de un radio determinado.

---

## 💻 Tecnologías y Herramientas

* **Lenguaje:** Java
* **Peticiones HTTP:** `java.net.http.HttpClient` nativo de Java.
* **Manejo de JSON:** Librería `Jackson` (`ObjectMapper`, anotaciones `@JsonProperty` y `@JsonIgnoreProperties`) para mapear las respuestas de las APIs a objetos Java.
* **Estructuras de Datos:** Uso intensivo de `ArrayList`, `HashSet` y `HashMap` para gestionar la caché, historiales y mapeos de códigos.

---

## 📸 Flujo de la Aplicación

A continuación, puedes ver un recorrido visual de cómo funciona el asistente en la terminal:

**1. Inicio de la Aplicación**
![Inicio de la Aplicación](<img width="937" height="353" alt="Explicacion_Aplicacion" src="https://github.com/user-attachments/assets/cb978f83-d415-4f1a-889b-f66699c217e1" />)

**2. Búsqueda e Información del País**
![Información del País Buscado](InfoPaisBuscado.png)

**3. Cambio de Divisas**
![Cambio de Divisas](cambio%20divisas.png)

**4. Búsqueda de Lugares en la Capital**
![Menú de Categorías de Búsqueda](buscarLugarYPlan.png)
![Resumen de los Parámetros de Búsqueda](resumen%20busqueda%20lugares.png)
![Resultados de los Lugares](resultado%20busqueda%20lugares.png)

**5. Fin de la Sesión y Guardado**
![Fin de la Sesión y Despedida](Despedida.png)

**Estructura del Proyecto:**
![Estructura de Carpetas del Proyecto](estructura_carpetas.png)

---

## ⚠️ Nota Importante sobre Configuración

> **Advertencia de Seguridad:** Este repositorio requiere el uso de claves API (API Keys) válidas para **ExchangeRate-API** y **Geoapify API**. Por motivos de seguridad, las claves privadas no deben subirse al repositorio público.
> 
> Para ejecutar este proyecto localmente, asegúrate de registrarte en los servicios mencionados, obtener tus propias API Keys y configurarlas correctamente en la clase `Buscador.java`.
