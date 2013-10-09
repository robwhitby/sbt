package com.springer.core.functional.foo

import com.springer.core.shared.utils.Clock
import org.scalatest._

class FunctionalTest extends FunSpec {
    it("should run a functional test") {
      new Clock().now
    }
}

