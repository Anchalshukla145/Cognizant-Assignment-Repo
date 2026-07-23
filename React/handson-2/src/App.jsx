import React, { Component } from 'react';

class Home extends Component {
  render() {
    return (
      <div>
        <h3>Home Component</h3>
        <p>Welcome to the Home page of Student Management Portal</p>
      </div>
    );
  }
}

class About extends Component {
  render() {
    return (
      <div>
        <h3>About Component</h3>
        <p>Welcome to the About page of the Student Management Portal</p>
      </div>
    );
  }
}

class Contact extends Component {
  render() {
    return (
      <div>
        <h3>Contact Component</h3>
        <p>Welcome to the Contact page of the Student Management Portal</p>
      </div>
    );
  }
}

export default class App extends Component {
  render() {
    return (
      <div>
        <h2>Student Management Portal</h2>
        <Home />
        <About />
        <Contact />
      </div>
    );
  }
}
