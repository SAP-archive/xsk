package com.sap.xsk.parser.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseParserErrorsModel {

  private String errorMessage;
  private int line;
  private int charPositionInLine;
}
