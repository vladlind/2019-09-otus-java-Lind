Приложение для теста GC:

Функция run() в классе Benchmark на каждом шаге итерации добавляет заданное в main количество элементов в arraylist, ждет 5 секунд, затем удаляет первую половину списка, снова ждет 5 секунд и выполняется дальше до тех пор, пока размер списка не дойдет до 0.97*(заданное количество элементов)

Тесты GC's:

В файле GC.odt (в корне проекта hw03_gc) находятся таблицы, содержащие результаты тестов трех типов GC - SerialGC, ParallelGC и G1. Каждая таблица представляет тест для определенного объема heap JVM и количества элементов генерируемого списка - LoopCounter. В рамках каждой таблицы для каждого типа GC проводятся три теста, отличающиеся параматерами GC в JVM - 1) дефолтными параметрами GC pause и GC interval pause, 2) -XX:MaxGCPauseMillis=100000, 3)
-XX:MaxGCPauseMillis=10. Графы таблицы заполнены временными интервалами, а также информация из логов выполнения приложения. 


Анализ данных таблиц:

1) Таблица для теста "-Xms5120m -Xmx5120m LoopCounter = 5M" не показала большой разницы во времени выполнения приложения для разных GC, кроме теста с большим stop-the-world периодом для G1 - приложение выполняется на ~1.5 секунды дольше. В логах видна разница в количестве и длительности stop-the-world пауз :

с -XX:MaxGCPauseMillis=10000:

_start:45156ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(1556 ms)
start:57634ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(640 ms)_

c дефолтным значением MaxGCpause:

_start:1874ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(184 ms)
start:2257ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(159 ms)
start:12733ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(118 ms)
start:13081ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(115 ms)
start:13410ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(125 ms)
start:23763ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(123 ms)
start:24050ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(112 ms)
start:34426ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(119 ms)
start:34719ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(113 ms)
start:34994ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(110 ms)
start:45412ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(133 ms)
start:45707ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(116 ms)
start:45986ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(108 ms)
start:56283ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(116 ms)
start:56562ms Name:G1 Young Generation, action:end of minor GC, gcCause:G1 Evacuation Pause(109 ms)_

По логам видно, что сумма множества коротких GC пауз меньше, чем сумма двух длинных пауз. Это связано с тем, что G1 для эффективной очистки памяти каждый раз при очистке памяти от бессылочных объетов также отмечает новых кандидатов на удаление - получается, чем чаще G1 на короткое время останавливает приложение, тем эффективнее и быстрее будет следующая пауза  - то есть G1 тем самым достигает некоего баланса между задержкой (Latency) и пропускной способностью приложения (Throughput).

Вывод по данной таблице - увеличение -XX:MaxGCPauseMillis для G1 только ухудшает показатали как для Latency, так и для Throughput.


2) В таблице "-Xms5120m -Xmx5120m LoopCounter = 15M" было увеличено число элементов генерируемого списка в 3 раза для вызывания ошибки OutOfMemory. В данной таблице стала более видимой разница между результатами выполнения приложения с  SerialGC и ParallelGC.  SerialGC делает такое же количество stop-the-world пауз, что и ParallelGC, но у первого они значительно длительнее, так как второй во времы паузы может имеет доступ ко всем вычислительным ресурсам машины (а SerialGC выполняет очистку в один поток), поэтому аналогичный объем работы по очистке мусора выполняется значительно быстрее.

SerialGC:

_List size after removal: 13125000
time:38865ms_

ParallelGC:

_List size after removal: 13125000
time:37390ms_

3) В таблице "-Xms10240m -Xmx10240m LoopCounter = 15M" преимущество во времени выполнения приложения с ParallelGC перед другими GC нагляднее всего. Незначительно проигрывает по времени G1, компенсируя общее время выполнения приложения очень короткими стоп-паузами, что делает G1 лидером по Latency среди тестируемых GC.


Оптимальный GC для тестируемого приложения:

Для тестируемого приложения главным ресурсом является Throughput, а не Latency, так как оно по сути симулирует передачу большого объема данных за временной промежуток. Поэтому длительные stop-the-world паузы, длящиеся по 1 секунде, не являются критичными для такого приложения. ParallelGC оптимально подходит для данного приложения.
