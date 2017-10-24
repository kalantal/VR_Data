package com.stmk.sddatavr.api

import com.stmk.sddatavr.search.dataset.mock.MockDataObject
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
@RestController
class MockDatasetController {

    private val counter: AtomicLong = AtomicLong()

    @RequestMapping("/mockdata", method = arrayOf(GET))
    fun mapMockData(): MockDataObject = MockDataObject(counter.incrementAndGet(), Array(50, {it}))
}
