/**
 * Hoja de scripts manejados en el mantenimiento de clima
 */
$(document).ready(function() {


});

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
function enableValidationRulesBandeja1() {

	var validator = $("#form-edit-ensayo").bootstrapValidator(
			
		{
		message: 'El valor no es v\u00e1lido',
		excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
		
			horaMuestreo: {
					validators : {
						regexp: {
							 regexp:/([01]?[0-9]|2[0-3]):[0-5][0-9]/,
							 message: 'Ingrese valor hora'
						},
						stringLength : {
							max : 8,
							message : 'Campo incorrecto'
						},
						notEmpty: {
	                        message: 'Ingrese valor de hora'
	                    } 
					}
	        },
	        
	        horaRecepcion: {
				validators : {
					regexp: {
						 regexp:/([01]?[0-9]|2[0-3]):[0-5][0-9]/,
						 message: 'Ingrese valor hora'
					},
					stringLength : {
						max : 8,
						message : 'Campo incorrecto'
					},
					notEmpty: {
                        message: 'Ingrese valor de hora'
                    } 
				}
	        },
        
        fechaInicioEnsayo: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de fecha'
                } 
			}
        },
        
        fechaMuestreo: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de fecha'
                } 
			}
        },
        
        fechaRecepcion: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de fecha'
                } 
			}
        },
        
        fechaReporte: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de fecha'
                } 
			}
        },
        
        volumenMuestra: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de volumen'
                } 
			}
        },
        planMuestreo: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de volumen'
                } 
			}
        },
        
        tiempoContacto: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de tiempo de contacto'
                } 
			}
        },
        
        horaInicioEnsayo: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de hora inicio ensayo'
                } 
			}
        },
        
        muestreador: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de muestreador'
                } 
			}
        },
        
        turno: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de turno'
                } 
			}
        },
        metodoMuestreo: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de muestreador'
                } 
			}
        },
        analista: {
			validators : {
				
				notEmpty: {
                    message: 'Ingrese valor de analista'
                } 
			}
        },
        sodio1: {
			validators : {
				regexp: {
					 regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
				},
				
				notEmpty: {
					message: 'Ingrese valor del campo'
              }
			}
      },
      
      sodio2: {
			validators : {
				regexp: {
					 regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
				},
				
				notEmpty: {
					message: 'Ingrese valor del campo'
               
                }
			}
    },
    
    sodio3: {
		validators : {
			regexp: {
				 regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 
				 message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
			},
			
			notEmpty: {
				message: 'Ingrese valor del campo'
          }
		}
  },
  
  sodio4: {
		validators : {
			regexp: {
				 regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
			},
		
			notEmpty: {
				message: 'Ingrese valor del campo'
            
        }
		}
  },
  
  cloro1: {
	validators : {
		notEmpty: {
          message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
      }
	}
  },
  
  cloro2: {
		validators : {
			regexp: {
				 regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
			},
			notEmpty: {
				message: 'Ingrese valor del campo'
	          
	      }
		}
	  },
   cloro3: {
			validators : {
				regexp: {
					 regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
				},
				notEmpty: {
					message: 'Ingrese valor del campo'
		          
		      }
			}
	},
	 cloro4: {
			validators : {
				regexp: {
					 regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
				},
				notEmpty: {
				  message: 'Ingrese valor del campo'
		          
		      }
			}
	},
	
	puntoMuestreo: {
		validators : {
			
			notEmpty: {
              message: 'Ingrese valor de  punto de muestreo'
          }
		}
	},
	
	ph: {
		validators : {
			regexp: {
				regexp:/^\d{1,2}(\.\d{1,2})?$/,
				 message: 'Ingrese valor de  ph, ejemplo: 9.5'
			},
			notEmpty: {
              message: 'Ingrese valor de ph'
          }
		}
	},
	
	temperatura: {
		validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor de temperatura correcto'
			},
			notEmpty: {
              message: 'Ingrese valor temperatura'
          }
		}
	},
	 "detalles[0].frasco": {
	    	validators : {
				notEmpty: {
	              message: 'Ingrese valor de frasco'
	          }
			}
	    },
	    "detalles[1].frasco": {
	    	validators : {
				notEmpty: {
	              message: 'Ingrese valor de frasco'
	          }
			}
	    },
	    "detalles[2].frasco": {
	    	validators : {
				notEmpty: {
	              message: 'Ingrese valor de frasco'
	          }
			}
	    },
	    "detalles[3].frasco": {
	    	validators : {
				notEmpty: {
	              message: 'Ingrese valor de frasco'
	          }
			}
	    },
	    "detalles[4].frasco": {
	    	validators : {
				notEmpty: {
	              message: 'Ingrese valor de frasco'
	          }
			}
	    },
	 
	
    "detalles[0].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[1].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[2].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[3].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[4].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    
    "detalles[0].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[1].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[2].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    
    "detalles[3].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[4].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    
    "detalles[0].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[1].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles[2].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles[3].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[4].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[0].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[1].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles[2].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor  correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles[3].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles[4].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
  
    
    "ph1": {
    	validators : {
			regexp: {
				regexp:/^\d{1,2}(\.\d{1,2})?$/,
				 message: 'Ingrese valor de  ph, ejemplo: 10.5'
			},
			notEmpty: {
              message: 'Ingrese valor de  ph'
          }
		}
    },
    
    "ph2": {
    	validators : {
			regexp: {
				regexp:/^\d{1,2}(\.\d{1,2})?$/,
				 message: 'Ingrese valor de  ph, ejm: 10.5'
			},
			notEmpty: {
              message: 'Ingrese valor de  ph'
          }
		}
    },
    
    "temp1": {
    	validators : {
			
			 regexp : {
					regexp : /^-?\d{1,3}(\.\d{1,2})?$/,
					message : "Ingrese 3 enteros con 2 decimales como m\u00E1ximo"
				},
				
				notEmpty: {
		              message: 'Ingrese valor de  temperatura'
		        }
		}
    },
    
    "temp2": {
    	validators : {
			
			 regexp : {
					regexp : /^-?\d{1,3}(\.\d{1,2})?$/,
					message : "Ingrese 3 enteros con 2 decimales como m\u00E1ximo"
				},
				
				notEmpty: {
		              message: 'Ingrese valor de  temperatura'
		        }
		}
    },
    
    "punto1": {
    	validators : {
			notEmpty: {
              message: 'Ingrese valor de punto muestreo'
          }
		}
    },
    
    "punto2": {
    	validators : {
			notEmpty: {
			  message: 'Ingrese valor de punto muestreo'
          }
		}
    },
    
    "detalles2[0].frasco": {
    	validators : {
			notEmpty: {
              message: 'Ingrese valor de frasco'
          }
		}
    },
    "detalles2[1].frasco": {
    	validators : {
			notEmpty: {
              message: 'Ingrese valor de frasco'
          }
		}
    },
    "detalles2[2].frasco": {
    	validators : {
			notEmpty: {
              message: 'Ingrese valor de frasco'
          }
		}
    },
    "detalles2[3].frasco": {
    	validators : {
			notEmpty: {
              message: 'Ingrese valor de frasco'
          }
		}
    },
    "detalles2[4].frasco": {
    	validators : {
			notEmpty: {
              message: 'Ingrese valor de frasco'
          }
		}
    },

	
    "detalles2[0].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[1].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[2].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[3].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[4].volumen": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de volumen'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    
    "detalles2[0].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[1].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[2].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    
    "detalles2[3].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[4].cl2Libre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2Libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    
    "detalles2[0].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[1].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles2[2].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles2[3].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[4].cl2Total": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cl2 Total'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[0].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[1].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "detalles2[2].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor  correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles2[3].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    "detalles2[4].cloroLibre": {
    	validators : {
			regexp: {
				regexp:/^\d{1,3}(\.\d{1,2})?$/,
				 message: 'Ingrese valor correcto de cloro libre'
			},
			notEmpty: {
              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
          }
		}
    },
    
    "bicromato1": {
    	validators : {
			notEmpty: {
              message: 'Ingrese Volumen A'
          }
		}
    },
    
    "bicromato2": {
	validators : {
		notEmpty: {
          message: 'Ingrese Volumen B'
      }
	}
	},
	
	"bicromato3": {
		validators : {
			notEmpty: {
	          message: 'Ingrese Volumen C'
	      }
		}
		},
		
	"solucion1": {
			validators : {
				notEmpty: {
		          message: 'Ingrese Volumen A'
		      }
			}
	},
	"solucion2": {
		validators : {
			notEmpty: {
	          message: 'Ingrese Volumen B'
	      }
		}
	},
	"solucion3": {
		validators : {
			notEmpty: {
	          message: 'Ingrese Volumen C'
	      }
		}
	},
	
	"observacion": {
		stringLength : {
			max : 300,
			message : 'No debe exceder 300 caracteres'
		},
		validators : {
			notEmpty: {
	          message: 'Ingrese observaci\u00f3n'
	      }
		}
	},
	
	
	 "detalles3[0].frasco": {
		 validators : {
				notEmpty: {
		          message: 'Campo incorrecto'
		     }
		 }
	   },
	   
	   "detalles3[0].volumen": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   },
	   
	   "detalles3[0].dosis": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   },
	   

	   "detalles3[0].cl2Libre": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   },
	   
	   "detalles3[0].cl2Comb": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   },
	   
	   "detalles3[0].cl2Total": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   },
	   "detalles3[0].cloroLibre": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   },
	   
	   "detalles3[0].cloroTotal": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   },
	   
	   "detalles3[0].demandaCloro": {
	    	validators : {
				regexp: {
					regexp:/^\d{1,3}(\.\d{1,2})?$/,
					 message: 'Ingrese valor del campo'
				},
				notEmpty: {
	              message: 'Valor m\u00E1ximo 3 enteros y 2 decimales'
	          }
			}
	   }
           
		}
	})	
	.on('success.form.bv', function(e, data) {
		e.preventDefault();
		
		let $codigoEnsayo = ($("#codigo").val() == "" || $("#codigo").val() == undefined ? 0 : $("#codigo").val());
		let $fechaMuestreo = $("#fechaMuestreo").val();
		let $puntoMuestreo1 = $("select[name=punto1]").val();
		let $puntoMuestreo2 = $("select[name=punto2]").val();
		
		$.ajax({
			   type: 'GET',
			   url: 'reporteEnsayoCloroValidation?codigo='+$codigoEnsayo+'&fechaMuestreo='+$fechaMuestreo+'&puntoMuestreo1='+$puntoMuestreo1+'&puntoMuestreo2='+$puntoMuestreo2,
			   success: function(result) {
				   
				   		   
				   if(!result){
					   $("#guardarButton").html('Guardar');
					   $('#guardarButton').removeAttr("disabled");
					   MYAPPL.showToast("genericNoOk", "El punto de muestreo ya ha sido utilizado para la fecha de muestreo. Cambiar los datos");
					}
					else{
						$("#guardarButton").html('Guardando...');
						$("#guardarButton").attr('disabled', 'disabled');
						
						var $nuevoTablaPoissonForm = $(e.target);
						console.log("guardando cambios para reporte");
						
						$.ajax({
							type: 'POST',
							url: 'ensayoCloroSave',
							data: $('#form-edit-ensayo').serialize(),
							success: function(result){
								$nuevoTablaPoissonForm.bootstrapValidator('resetForm', false);
								
								$('.container_save_reporte').html(result);
							},
							complete: function() {
								
								$("#buttonCloseDetail").click();
								$("#guardarButton").html('Guardar');
								$('#guardarButton').removeAttr("disabled");
								
								$('#ensayoCloroBandeja').DataTable().ajax.reload();
							}
						});
					}
			   }
		});
		
	});	
}

			
			
