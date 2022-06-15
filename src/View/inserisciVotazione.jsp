<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DA SOSTITUIRE COME PARAMETRO</title>
    <link href="./css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
</head>

<body>
    <!-- INIZIO -->
    <nav class="navbar navbar-expand-lg bg-light fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">LOGO</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="d-flex" role="search">
                    <a class="btn btn-primary me-2" href="">Login</a>
                    <a class="btn btn-secondary" href="">Registrati</a>
                </div>
            </div>
        </div>
    </nav>


    <!-- INIZIO -->
    <div class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
        <div class="col-4 row justify-content-center align-items-center">
            <form class="col-12 row justify-content-center">
                <h2>Inserisci nuova votazione</h2>
                <div class="mb-3">
                    <div class="mb-3">
                        <label for="email" class="form-label">Testo azione significativa (A)</label>
                        <textarea class="form-control" id="text" style="height: 100px"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="customRange1" class="form-label">Azione significativa (E)</label>
                        <select class="form-select" id="corsoDiLaurea">
                            <option selected disabled hidden></option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="customRange1" class="form-label">CFU (A)</label>
                        <input type="range" min="0" max="100" step="10" class="form-range" id="customRange1">
                    </div>
                    <div class="mb-3">
                        <label for="customRange1" class="form-label">Professore (E)</label>
                        <select class="form-select" id="corsoDiLaurea">
                            <option selected disabled hidden></option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>
                    <button type="submit" class="w-100 btn btn-success mt-3">Inserisci</button>
                </div>
            </form>
        </div>
    </div>
    <!-- FINE -->
</body>

</html>