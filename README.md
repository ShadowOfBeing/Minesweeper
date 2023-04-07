Консольная версия знаменитой игры сапёр. Написана в рамках прохождения курса по языку Kotlin, в частности изучения темы про ООП https://hyperskill.org/projects/8

Описание:
1) При запуске программы в консоль выводится сообщение "How many mines do you want on the field?", приглашающее игрока задать количество мин на поле
2) После того как игрок введёт число мин далее в консоль выводится минное поле с сеткой координат (все клетки закрыты, а мины в заданном количестве разбросаны по карте в случайном порядке), ниже выводится фраза "Set/unset mine marks or claim a cell as free:" и у игрока появляется две опции:
- командой <b><число координаты по оси X> <число координаты по оси Y> mine</b> пометить поле миной/снять метку мины с поля
Например команда 1 1 mine пометит миной клетку в первой колонке первого ряда (т.е. мы отметили клетку как предположительно ячейку с миной). Повторная команда 1 1 mine снимет метку мины с поля.
- командой <b><число координаты по оси X> <число координаты по оси Y> free</b> открыть клетку
Например команда 1 1 free откроет клетку в первой колонке первого ряда и если там мина, то игра закончится поражением игрока, если там число (показывающее число мин вокруг текущей клетки), то игра продолжается, а если там пустая клетка, то вокруг неё по периметру проверяются все клетки и если найдена ещё одна пустая клетка, то уже по её периметру проверяются другие клетки, т.е. запускается цепная реакция (мины остаются закрытыми клетками).
3) По правилам игры самая первая открытая ячейка не может быть миной. Таким образом, если первая открытая ячейка всё же окажется миной, то скрипт на лету перерисует карту таким образом, чтобы эта клетка была свободна от мин, при этом будет создана полностью новая карта, с другим расположением мин (но сохранятся метки, проставленные игроком).
4) Игрок побеждает если откроет все клетки свободные от мин или же пометит минами все клетки с минами.
