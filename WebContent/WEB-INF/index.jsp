<%@ page import="Bean.User" %>
<jsp:include page="header.jsp"/>

<!-- Masthead -->
<header class="masthead text-white text-center">
  <div class="overlay"></div>
  <div class="container">
    <div class="row">
      <div class="col-xl-9 mx-auto">
        <h1 class="mb-5">Shorten your URLs right away !</h1>
      </div>
      <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
        <form action='/link' method='POST'>
          <div class="form-row">
            <div class="col-12 col-md-9 mb-2 mb-md-0">
              <% if (request.getAttribute("origin")!=null) { %>
              <input type="text" name="url" class="form-control form-control-lg" placeholder="Enter your url...." value="<%= request.getAttribute("origin").toString() %>">
              <div>
                Shorted url : <a href="http://localhost:8080/redirect/<%= request.getAttribute("target").toString() %>">http://localhost:8080/redirect/<b><%= request.getAttribute("target").toString() %></b></a>
              </div>
              <% } else { %>
              <input type="text" name="url" class="form-control form-control-lg" placeholder="Enter your url....">
              <% } %>
            </div>
            <div class="col-12 col-md-3">
              <button type="submit" class="btn btn-block btn-lg btn-primary">GO!</button>
            </div>
            <%
              User user = (User) session.getAttribute("user");

              if (user != null) { %>

            <div class="col-12">
              <input type="checkbox" id="with_captcha" name="with_captcha" value="1">
              <label for="with_captcha">Secure by captcha</label>
            </div>
            <div class="col-12">
              <input type="checkbox" id="with_pass" name="with_password" value="1">
              <label for="with_pass">Secure by password</label>
            </div>
            <div class="col-12" id="show_pass" style="display:none">
              <input type="text" name="password" placeholder="Mot de passe" class="form-control form-control-lg">
            </div>
            <div class="col-6" id="">
              <label>Available from</label>
              <input type="date" name="available_from" placeholder="12/12/12" class="form-control form-control-lg">
            </div>
            <div class="col-6" id="">
              <label>Available to</label>
              <input type="date" name="available_to" placeholder="13/12/12" class="form-control form-control-lg">
            </div>
            <div class="col-12" id="">
              <label>Max clics</label>
              <input type="number" name="max_clic" placeholder="Max clics" class="form-control form-control-lg">
            </div>
            <% } %>
          </div>
        </form>



        <%
          String link = (String) request.getAttribute("link");

          if( link != null && !link.isEmpty() ) { %>
        <div>
          <p>Great your link is ready ! <br>
            <a href="<%= link %>"><%= link %></a>
          </p>
        </div>
        <% } %>
      </div>
    </div>
  </div>
</header>

<!-- Icons Grid -->
<section class="features-icons bg-light text-center">
  <div class="container">
    <div class="row">
      <div class="col-lg-4">
        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
          <div class="features-icons-icon d-flex">
            <i class="icon-screen-desktop m-auto text-primary"></i>
          </div>
          <h3>Fully Responsive</h3>
          <p class="lead mb-0">This theme will look great on any device, no matter the size!</p>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
          <div class="features-icons-icon d-flex">
            <i class="icon-layers m-auto text-primary"></i>
          </div>
          <h3>Bootstrap 4 Ready</h3>
          <p class="lead mb-0">Featuring the latest build of the new Bootstrap 4 framework!</p>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="features-icons-item mx-auto mb-0 mb-lg-3">
          <div class="features-icons-icon d-flex">
            <i class="icon-check m-auto text-primary"></i>
          </div>
          <h3>Easy to Use</h3>
          <p class="lead mb-0">Ready to use with your own content, or customize the source files!</p>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Image Showcases -->
<section class="showcase">
  <div class="container-fluid p-0">
    <div class="row no-gutters">

      <div class="col-lg-6 order-lg-2 text-white showcase-img" style="background-image: url('img/bg-showcase-1.jpg');"></div>
      <div class="col-lg-6 order-lg-1 my-auto showcase-text">
        <h2>Fully Responsive Design</h2>
        <p class="lead mb-0">When you use a theme created by Start Bootstrap, you know that the theme will look great on any device, whether it's a phone, tablet, or desktop the page will behave responsively!</p>
      </div>
    </div>
    <div class="row no-gutters">
      <div class="col-lg-6 text-white showcase-img" style="background-image: url('img/bg-showcase-2.jpg');"></div>
      <div class="col-lg-6 my-auto showcase-text">
        <h2>Updated For Bootstrap 4</h2>
        <p class="lead mb-0">Newly improved, and full of great utility classes, Bootstrap 4 is leading the way in mobile responsive web development! All of the themes on Start Bootstrap are now using Bootstrap 4!</p>
      </div>
    </div>
    <div class="row no-gutters">
      <div class="col-lg-6 order-lg-2 text-white showcase-img" style="background-image: url('img/bg-showcase-3.jpg');"></div>
      <div class="col-lg-6 order-lg-1 my-auto showcase-text">
        <h2>Easy to Use &amp; Customize</h2>
        <p class="lead mb-0">Landing Page is just HTML and CSS with a splash of SCSS for users who demand some deeper customization options. Out of the box, just add your content and images, and your new landing page will be ready to go!</p>
      </div>
    </div>
  </div>
</section>

<!-- Testimonials -->
<section class="testimonials text-center bg-light">
  <div class="container">
    <h2 class="mb-5">What people are saying...</h2>
    <div class="row">
      <div class="col-lg-4">
        <div class="testimonial-item mx-auto mb-5 mb-lg-0">
          <img class="img-fluid rounded-circle mb-3" src="img/testimonials-1.jpg" alt="">
          <h5>Margaret E.</h5>
          <p class="font-weight-light mb-0">"This is fantastic! Thanks so much guys!"</p>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="testimonial-item mx-auto mb-5 mb-lg-0">
          <img class="img-fluid rounded-circle mb-3" src="img/testimonials-2.jpg" alt="">
          <h5>Fred S.</h5>
          <p class="font-weight-light mb-0">"Bootstrap is amazing. I've been using it to create lots of super nice landing pages."</p>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="testimonial-item mx-auto mb-5 mb-lg-0">
          <img class="img-fluid rounded-circle mb-3" src="img/testimonials-3.jpg" alt="">
          <h5>Sarah	W.</h5>
          <p class="font-weight-light mb-0">"Thanks so much for making these free resources available to us!"</p>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Call to Action -->
<section class="call-to-action text-white text-center">
  <div class="overlay"></div>
  <div class="container">
    <div class="row">
      <div class="col-xl-9 mx-auto">
        <h2 class="mb-4">Ready to get started? Sign up now!</h2>
      </div>
      <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
        <form>
          <div class="form-row">
            <div class="col-12 col-md-9 mb-2 mb-md-0">
              <input type="email" class="form-control form-control-lg" placeholder="Enter your email...">
            </div>
            <div class="col-12 col-md-3">
              <button type="submit" class="btn btn-block btn-lg btn-primary">Sign up!</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</section>
<jsp:include page="footer.jsp" />