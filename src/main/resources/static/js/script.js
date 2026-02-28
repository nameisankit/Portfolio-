// Mobile Menu Toggle
document.addEventListener('DOMContentLoaded', () => {
    const menuBtn = document.getElementById('mobileMenuBtn');
    const navbar = document.getElementById('navbar');
    const menuIcon = document.getElementById('menuIcon');

    // Create overlay
    const overlay = document.createElement('div');
    overlay.className = 'mobile-menu-overlay';
    document.body.appendChild(overlay);

    function openMenu() {
        navbar.classList.add('open');
        overlay.classList.add('open');
        menuIcon.className = 'fas fa-times';
        document.body.style.overflow = 'hidden';
    }

    function closeMenu() {
        navbar.classList.remove('open');
        overlay.classList.remove('open');
        menuIcon.className = 'fas fa-bars';
        document.body.style.overflow = '';
    }

    if (menuBtn) {
        menuBtn.addEventListener('click', () => {
            navbar.classList.contains('open') ? closeMenu() : openMenu();
        });
    }

    overlay.addEventListener('click', closeMenu);

    // Close menu on nav link click
    document.querySelectorAll('.nav-item').forEach(link => {
        link.addEventListener('click', closeMenu);
    });
});

// Loading Animation
window.addEventListener('load', () => {
  setTimeout(() => {
      document.querySelector('.loader-wrapper').style.opacity = '0';
      setTimeout(() => {
          document.querySelector('.loader-wrapper').style.display = 'none';
      }, 500);
  }, 2000);
});

//document.addEventListener('mousedown', () => {
//  cursor.style.transform = 'translate(-50%, -50%) scale(0.8)';
//});
//
//document.addEventListener('mouseup', () => {
//  cursor.style.transform = 'translate(-50%, -50%) scale(1)';
//});

// Scrolled Header
window.addEventListener('scroll', () => {
  const header = document.querySelector('.header');
  if (window.scrollY > 100) {
      header.classList.add('scrolled');
  } else {
      header.classList.remove('scrolled');
  }
});

// Intersection Observer for animations
const observerOptions = {
  threshold: 0.1,
  rootMargin: "0px 0px -50px 0px"
};

const projectObserver = new IntersectionObserver((entries) => {
  entries.forEach((entry, index) => {
      if (entry.isIntersecting) {
          setTimeout(() => {
              entry.target.classList.add('animated');
          }, index * 200);
      }
  });
}, observerOptions);

document.querySelectorAll('.project-card').forEach(card => {
  projectObserver.observe(card);
});

const timelineObserver = new IntersectionObserver((entries) => {
  entries.forEach((entry) => {
      if (entry.isIntersecting) {
          entry.target.classList.add('animated');
      }
  });
}, observerOptions);

document.querySelectorAll('.timeline-content').forEach(item => {
  timelineObserver.observe(item);
});

const achievementObserver = new IntersectionObserver((entries) => {
  entries.forEach((entry, index) => {
      if (entry.isIntersecting) {
          setTimeout(() => {
              entry.target.classList.add('animated');
          }, index * 200);
      }
  });
}, observerOptions);

document.querySelectorAll('.achievement-card').forEach(card => {
  achievementObserver.observe(card);
});

// Smooth scroll
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
  anchor.addEventListener('click', function (e) {
      e.preventDefault();

      const target = document.querySelector(this.getAttribute('href'));
      if (!target) return;

      window.scrollTo({
          top: target.offsetTop - 100,
          behavior: 'smooth'
      });
  });
});

// ==================== PROJECTS SLIDER ====================
let currentProjectSlide = 0;

function updateProjectSlider() {
  const track = document.querySelector('.projects-track');
  const dots = document.querySelectorAll('.projects-dot');
  const totalSlides = document.querySelectorAll('.project-slide').length;

  if (!track || totalSlides === 0) return;

  track.style.transform = `translateX(-${currentProjectSlide * 100}%)`;

  dots.forEach((dot, index) => {
    dot.classList.toggle('active', index === currentProjectSlide);
  });
}

function changeProjectSlide(direction) {
  const totalSlides = document.querySelectorAll('.project-slide').length;
  if (totalSlides === 0) return;

  currentProjectSlide += direction;
  if (currentProjectSlide < 0) currentProjectSlide = totalSlides - 1;
  if (currentProjectSlide >= totalSlides) currentProjectSlide = 0;

  updateProjectSlider();
}

function goToProjectSlide(index) {
  currentProjectSlide = index;
  updateProjectSlider();
}

document.addEventListener('DOMContentLoaded', () => {
  updateProjectSlider();
});


// ==================== ACHIEVEMENTS SLIDER ====================
let currentAchievementSlide = 0;

function updateAchievementSlider() {
  const track = document.querySelector('.achievements-track');
  const dots = document.querySelectorAll('.achievements-dot');
  const totalSlides = document.querySelectorAll('.achievement-slide').length;

  if (!track || totalSlides === 0) return;

  track.style.transform = `translateX(-${currentAchievementSlide * 100}%)`;

  dots.forEach((dot, index) => {
    dot.classList.toggle('active', index === currentAchievementSlide);
  });
}

function changeAchievementSlide(direction) {
  const totalSlides = document.querySelectorAll('.achievement-slide').length;
  if (totalSlides === 0) return;

  currentAchievementSlide += direction;
  if (currentAchievementSlide < 0) currentAchievementSlide = totalSlides - 1;
  if (currentAchievementSlide >= totalSlides) currentAchievementSlide = 0;

  updateAchievementSlider();
}

function goToAchievementSlide(index) {
  currentAchievementSlide = index;
  updateAchievementSlider();
}

document.addEventListener('DOMContentLoaded', () => {
  updateAchievementSlider();
});


// ================= AUTO SLIDE for PROJECTS & ACHIEVEMENTS =================

// ⏳ time gap (milliseconds) — 5000 = 5 seconds
const SLIDE_INTERVAL = 3000;

// Project auto-slide
setInterval(() => {
  changeProjectSlide(1);
}, SLIDE_INTERVAL);

// Achievement auto-slide
setInterval(() => {
  changeAchievementSlide(1);
}, SLIDE_INTERVAL);
