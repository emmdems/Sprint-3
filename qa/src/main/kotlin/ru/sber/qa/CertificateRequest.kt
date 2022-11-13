package ru.sber.qa

import kotlin.random.Random

/**
 * Запрос на изготовление справки.
 */
class CertificateRequest(
    val employeeNumber: Long,
    val certificateType: CertificateType,
) {

    fun process(hrEmployeeNumber: Long): Certificate =
        Certificate(certificateRequest = this, processedBy = hrEmployeeNumber, data = Scanner.getScanData())
}
