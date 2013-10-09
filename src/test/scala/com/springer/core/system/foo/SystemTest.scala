package com.springer.core.system.foo

import com.springer.core.shared.utils.Clock
import org.scalatest._

class SystemTest extends FunSpec {
    it("should run a system test") {
      new Clock().now    
    }
}

