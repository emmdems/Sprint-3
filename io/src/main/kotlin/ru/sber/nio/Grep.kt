package ru.sber.nio

import java.io.File
import java.lang.Exception
import java.nio.charset.Charset
import java.nio.file.Files
import java.util.LinkedList
import kotlin.io.path.useLines

/**
 * Реализовать простой аналог утилиты grep с использованием калссов из пакета java.nio.
 */
class Grep {
    /**
     * Метод должен выполнить поиск подстроки subString во всех файлах каталога logs.
     * Каталог logs размещен в данном проекте (io/logs) и внутри содержит другие каталоги.
     * Результатом работы метода должен быть файл в каталоге io(на том же уровне, что и каталог logs), с названием result.txt.
     * Формат содержимого файла result.txt следующий:
     * имя файла, в котором найдена подстрока : номер строки в файле : содержимое найденной строки
     * Результирующий файл должен содержать данные о найденной подстроке во всех файлах.
     * Пример для подстроки "22/Jan/2001:14:27:46":
     * 22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] "POST /files HTTP/1.1" 200 - "-"
     */
    fun find(subString: String) {
        val path = File("io/logs").toPath()
        val fileResult = File("io/result.txt")
        val linesForResult: LinkedList<String> = LinkedList()
        try { Files.find(path, 2, { p, _ -> p.toString().endsWith(".log") }).
                forEach {
                    val fName = it.fileName
                    it.useLines {
                        var strNum = 0
                        it.forEach {
                            strNum += 1
                            when (it.contains(subString)) {false -> {}true -> { linesForResult.push("$fName : $strNum : $it") } }
                        }
                    }
                }
            fileResult.writer(Charset.forName("UTF-8")).use { w -> linesForResult.sorted().forEach { line -> w.write(line + "\n") } }
            linesForResult.clear()
        }catch (e: Exception) { print(e.message) }
    }
}
