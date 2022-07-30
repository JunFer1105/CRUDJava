<%-- 
    Document   : Principal
    Created on : 29/07/2022, 4:30:04 p. m.
    Author     : Juan Fernando Arango <junfer.1105@gmail.com>
--%>

<%@page import="com.fernando.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
    Usuario usuario;
    if ((usuario = (Usuario)sesion.getAttribute("usuario"))==null){
        response.sendRedirect("index.html");
        return ;
    };
    %>
<html>
    <head>
        <title>Prueba BigView</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.1/css/jquery.dataTables.css">
        <!--link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"-->

        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.1/js/jquery.dataTables.js"></script>
        <script src="Assets/js/gestorPrincipal.js" type="text/javascript"></script>

        
    </head>
    <body>
        <div class="container">

            <div class="row">
                <div class="col-12 text-center" id="titulo">
                    <h1>Menu principal </h1>
                    <h2>Bienvenido <% 
                        out.print(usuario.getNombre_usuario());
                        %></h2>
                </div>
                <hr>
                <div class="col-6 text-center">
                    <div class="card" style="width: 18rem; margin: auto;">
                        <img src="Assets/img/especializacionIcon.png" class="card-img-top" alt="..."/>
                        <div class="card-body">
                            <h5 class="card-title">Especializacion</h5>
                            <p class="card-text">CRUD especializacion</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-primary" onclick="getAllEspecialidades()">Traer lista especialidades</button>
                                </li>
                                <li class="list-group-item">
                                    <label>Id especialidad</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="id_especialidad" placeholder="Identificador">
                                    </div>
                                    <div class="input-group mb-3">
                                        <button type="button" class="btn btn-primary" onclick="getEspecialidadesPorId(id_especialidad)">Traer especialidades</button>
                                    </div>
                                </li> 
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-6" > 
                    <div class="card" style="width: 18rem; margin: auto;">
                        <img src="Assets/img/medicoIcon.png" class="card-img-top" alt="..."/>
                        <div class="card-body">
                            <h5 class="card-title">Medico</h5>
                            <p class="card-text">CRUD medico</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-primary" onclick="getAllMedicos()">Traer lista medicos</button>
                                </li>
                                <li class="list-group-item">
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="id_medico" placeholder="Identificador">
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="nombre_medico" placeholder="Nombre">
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="telefono_medico" placeholder="Telefono">
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="direccion_medico" placeholder="Direccion">
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="date" class="form-control" id="fec_nacimiento_medico" placeholder="Fecha">
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="id_especialidad_medico" placeholder="Identificador especialidad">
                                    </div>
                                    <div class="input-group mb-3">
                                        <button type="button" class="btn btn-success" onclick="insertarMedico(id_medico, nombre_medico,telefono_medico,direccion_medico,fec_nacimiento_medico,id_especialidad_medico)">Insertar medico</button>
                                    </div>
                                    <div class="input-group mb-3">
                                        <button type="button" class="btn btn-success" onclick="actualizarMedico(id_medico, nombre_medico,telefono_medico,direccion_medico,fec_nacimiento_medico,id_especialidad_medico)">Actualizar datos medico</button>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="id_medico_eliminar" placeholder="Identificador medico">
                                    </div>
                                    <div class="input-group mb-3">
                                        <button type="button" class="btn btn-danger" onclick="deleteMedico(id_medico_eliminar)">Eliminar medico</button>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <label>Buscar por especialidad</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="id_especialidad_busqueda_medico" placeholder="Identificador">
                                    </div>
                                    <div class="input-group mb-3">
                                        <button type="button" class="btn btn-primary" onclick="getMedicoPorEspecialidad(id_especialidad_busqueda_medico)">Traer especialidades</button>
                                    </div>
                                </li> 
                            </ul>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </body>
    <!--modal-->
    <div class="modal fade" id="responseModal" tabindex="-1" aria-labelledby="responseModal" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">CRUD Hospital</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="responseModal_body">
                </div>
            </div>
        </div>
    </div>
</html>
