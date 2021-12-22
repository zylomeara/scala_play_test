package utils

import io.circe.generic.extras.Configuration

trait CirceConfiguration {
  implicit val configuration: Configuration = Configuration
    .default
    .withDefaults
    .withSnakeCaseMemberNames
    .withSnakeCaseConstructorNames
}
