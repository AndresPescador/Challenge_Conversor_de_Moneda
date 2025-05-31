# Challenge_Conversor_de_Moneda
Este es un programa de un conversor de monedas hecho en Java, el cual hace uso de la Api: [ExchangeRate API](https://www.exchangerate-api.com/) y Gson para el mandejo/análisis de JSON.


## Funcionalidades del Proyecto

* Permite conversiones entre múltiples divisas de distintos países.

* Realiza consultas a la API para obtener valores actualizados en tiempo real.

* Ofrece una interfaz a través de la línea de comandos.

* Incluye manejo de excepciones para evitar fallos ante entradas incorrectas del usuario.

## Tecnologías empleadas

* Java 17+
* [ExchangeRate API](https://www.exchangerate-api.com/)
* Gson (Google) para parsing de JSON
* HTTPClient (Java 11+) para llamadas a la API

## 📋 Ejemplo de uso

```
1) Dólar => Peso argentino (USD -> ARS)
2) Euro => Dólar (EUR -> USD)
...
9) Salir

Ingrese una opción: 1
Ingrese la cantidad a convertir: 100

Tasa de cambio (USD -> ARS): 1195,3300
Resultado de la conversión: 100.00 USD = 119533,00 ARS
Última actualización de tasas: Sat, 31 May 2025 00:00:01 +0000
```

---

## 📦 Estructura del proyecto

```
ConversorMonedasJava/
├── src/
│   └── Main.java
├── lib/
│   └── gson-<version>.jar
├── README.md
└── .gitignore
```
