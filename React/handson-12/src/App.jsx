import React, { useState } from 'react';

function GuestView(props) {
  return (
    <div>
      <h3>Browse Flights</h3>
      <button onClick={props.onLogin}>Log In</button>
      <p>Flight 1: Kolkata to Delhi - INR 5500</p>
      <p>Flight 2: Mumbai to Bengaluru - INR 4800</p>
    </div>
  );
}

function UserView(props) {
  const [selected, setSelected] = useState('AI-101');
  const [msg, setMsg] = useState('');

  const handleBooking = (e) => {
    e.preventDefault();
    const ref = 'TX-' + Math.floor(Math.random() * 900000);
    setMsg('Booking successful! Ref: ' + ref);
  };

  return (
    <div>
      <h3>Welcome back, {props.name}!</h3>
      <button onClick={props.onLogout}>Log Out</button>
      <form onSubmit={handleBooking}>
        <select value={selected} onChange={e => setSelected(e.target.value)}>
          <option value="AI-101">AI-101</option>
          <option value="6E-202">6E-202</option>
        </select>
        <button type="submit">Confirm Booking</button>
      </form>
      {msg && <p>{msg}</p>}
    </div>
  );
}

export default function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  return (
    <div>
      <h2>Ticket Booking App</h2>
      {loggedIn ? (
        <UserView name="Anchal Shukla" onLogout={() => setLoggedIn(false)} />
      ) : (
        <GuestView onLogin={() => setLoggedIn(true)} />
      )}
    </div>
  );
}
