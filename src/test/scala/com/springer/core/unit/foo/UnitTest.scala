package com.springer.core.unit.foo

import com.springer.core.shared.utils.Clock
import org.scalatest._

class UnitTest extends FunSpec {
    it("should run a unit test") {
      new Clock().now
    }
}

