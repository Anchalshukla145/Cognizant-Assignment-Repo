import React, { Component } from 'react';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: []
    };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts?_limit=5')
      .then(res => res.json())
      .then(data => {
        this.setState({ posts: data });
      })
      .catch(err => {
        alert('Error loading posts: ' + err.message);
      });
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error) {
    alert('Error: ' + error.message);
  }

  render() {
    return (
      <div>
        {this.state.posts.map(post => (
          <div key={post.id} style={{ border: '1px solid #ccc', margin: '10px 0', padding: '10px' }}>
            <h4>{post.title}</h4>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default class App extends Component {
  render() {
    return (
      <div>
        <h2>Posts List</h2>
        <Posts />
      </div>
    );
  }
}
