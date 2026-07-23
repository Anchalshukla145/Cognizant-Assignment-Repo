import React, { useState } from 'react';

function ListofPlayers() {
  const players = [
    { name: 'Virat Kohli', score: 85 },
    { name: 'Rohit Sharma', score: 92 },
    { name: 'Shikhar Dhawan', score: 45 },
    { name: 'K. L. Rahul', score: 68 },
    { name: 'Rishabh Pant', score: 72 },
    { name: 'Hardik Pandya', score: 55 },
    { name: 'Ravindra Jadeja', score: 64 },
    { name: 'R. Ashwin', score: 38 },
    { name: 'Jasprit Bumrah', score: 15 },
    { name: 'Mohammed Shami', score: 10 },
    { name: 'Y. Chahal', score: 5 }
  ];

  const below70 = players.filter(p => p.score < 70);

  return (
    <div>
      <h4>Players score below 70</h4>
      <ul>
        {below70.map((p, idx) => (
          <li key={idx}>{p.name} - {p.score}</li>
        ))}
      </ul>
    </div>
  );
}

function IndianPlayers() {
  const squad = ['Virat', 'Rohit', 'Shikhar', 'Rahul', 'Jadeja', 'Bumrah'];
  const [first, second, third, fourth, fifth, sixth] = squad;
  const oddTeam = [first, third, fifth];
  const evenTeam = [second, fourth, sixth];

  const T20players = ['Suryakumar Yadav', 'Ishan Kishan'];
  const RanjiTrophyPlayers = ['Ajinkya Rahane', 'Cheteshwar Pujara'];
  const mergedSquad = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div>
      <p>Odd Team: {oddTeam.join(', ')}</p>
      <p>Even Team: {evenTeam.join(', ')}</p>
      <p>Merged Squad: {mergedSquad.join(', ')}</p>
    </div>
  );
}

export default function App() {
  const [flag, setFlag] = useState(true);

  return (
    <div>
      <h2>Cricket App</h2>
      <button onClick={() => setFlag(!flag)}>Toggle</button>
      {flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}
