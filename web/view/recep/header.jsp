<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">ABClinic</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/ABClinic/WaitingListController">Waiting List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Accept List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/ABClinic/CustomerSearchController?searching=1">Search</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/ABClinic/addnewcus">Create Order</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
    </ul>
  </div>
</nav>
