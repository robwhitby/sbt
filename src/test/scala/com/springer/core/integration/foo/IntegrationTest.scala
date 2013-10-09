package com.springer.core.integration.foo

import com.springer.core.shared.utils.Clock
import org.scalatest._

class IntegrationTest extends FunSpec {
    it("should run a integration test") {
      new Clock().now
    }
}

