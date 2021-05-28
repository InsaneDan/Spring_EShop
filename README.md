# ![Spring logo](https://github.com/InsaneDan/InsaneDan/blob/main/spring.png) 
##  Разработка интернет-магазина на Spring Framework
<details>
<summary>Урок 1. Собираем базовое приложение</summary>

  1. При оформлении заказа нужно указать доп информацию: телефон, адрес доставки  
  2. Регистрация пользователей через отправку JSON  
     { "username": "bob", "password": "123", "email": "bob@gmail.com" }
  3. *Исследовательская* Загрузка товаров из файла
</details>
<details>
<summary>Урок 2. Полезные блоки</summary>

1. AOP: С помощью АОП посчитайте по каждому сервису суммарное время, уходящее на выполнение методов этих сервисов. 
   И по endpoint'у /statistic выдайте полученную статиcтику клиенту. Пример:  
        
        ProductService: 1200 ms
        OrderService: 95 ms
        UserService: 2000 ms

2. *Вынести список заказов пользователя на отдельную страницу
</details>

<details>
<summary>Урок 3. Spring Expression Language. AOP</summary>

1. Сделать из корзины сессионный бин
</details>

