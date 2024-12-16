package com.gabryel.mercadolivro.extension

import com.gabryel.mercadolivro.dto.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.CustomerSaveDTO
import com.gabryel.mercadolivro.dto.CustomerUpdateDTO
import com.gabryel.mercadolivro.model.CustomerModel

/**
 * Converts this [CustomerDetailDTO] into a [CustomerModel].
 *
 * @return a new [CustomerModel] with the same ID, name and email as this [CustomerDetailDTO].
 */
fun CustomerModel.toCustomerDetailDTO() = CustomerDetailDTO(id, name, email)

/**
 * Converts this [CustomerSaveDTO] into a [CustomerModel].
 *
 * @return a new [CustomerModel] with the same name and email as this [CustomerSaveDTO].
 */
fun CustomerSaveDTO.toCustomerModel() = CustomerModel(name = name, email = email)


/**
 * Converts this [CustomerUpdateDTO] into a [CustomerModel].
 *
 * @return a new [CustomerModel] with the same name and email as this [CustomerUpdateDTO].
 */
fun CustomerUpdateDTO.toCustomerModel() = CustomerModel(name = name, email = email)