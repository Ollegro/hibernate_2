# Предложения по улучшению

## таблица "film_text"
Таблица «film_text» не связана с таблицей «film», поэтому ей необходимо получить внешний ключ;

## таблица "address"
Таблицу "address"  лучше сделать embedded;

## таблица"film"
Столбец «original_language_id» не должен иметь значение NULL, поскольку фильм всегда имеет какую то озвучку;
Столбец «special_features» имеет несколько значений для одного Film_id, лучше создать встроенную таблицу, так как один фильм может иметь несколько специальных функций;