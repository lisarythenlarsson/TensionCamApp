package net.codejava.analyse;

/**
 * @author Martin Falk Danauskis
 * 
 * Copyright 2013 Fredrik Johansson, Lisa Ryth�n Larsson, Martin Falk Danauskis, Max Dubois
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 
 * Method that executes a commandline
 */
public class CommandExecution {
	public CommandExecution(String commandline) {
		try {
			Runtime.getRuntime().exec(commandline);//Creates a process that executes a commandline, in this case a .bat
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}
