package com.eurolearn.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginInfo {
	
	private long cpf; 
	private String email;
	private String senha;

}
