import React, { useState } from 'react';

function BookDetails() {
  return (
    <div>
      <h4>Book Details</h4>
      <p>Title: React Patterns</p>
      <p>Author: Intern Academy</p>
    </div>
  );
}

function BlogDetails() {
  return (
    <div>
      <h4>Blog Details</h4>
      <p>Title: ES6 Spread Revolution</p>
      <p>Views: 12045</p>
    </div>
  );
}

function CourseDetails() {
  return (
    <div>
      <h4>Course Details</h4>
      <p>Title: Java FSE Accelerator</p>
      <p>Duration: 12 Weeks</p>
    </div>
  );
}

export default function App() {
  const [selection, setSelection] = useState('books');

  let optionalCourseElement;
  if (selection === 'courses') {
    optionalCourseElement = <CourseDetails />;
  }

  return (
    <div>
      <h2>Blogger App</h2>
      <button onClick={() => setSelection('books')}>Show Books</button>
      <button onClick={() => setSelection('blogs')}>Show Blogs</button>
      <button onClick={() => setSelection('courses')}>Show Courses</button>

      <div>
        {selection === 'books' ? <BookDetails /> : null}
        {selection === 'blogs' && <BlogDetails />}
        {selection === 'courses' && optionalCourseElement}
      </div>
    </div>
  );
}
