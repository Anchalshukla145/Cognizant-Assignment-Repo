import React from 'react';
import styles from './CohortDetails.module.css';

export default function App() {
  const cohorts = [
    { id: 1, name: 'Java Full Stack Developer', code: 'CH_JFS_2026', status: 'ongoing' },
    { id: 2, name: 'AWS Cloud Architecture', code: 'CH_AWS_2025', status: 'completed' },
    { id: 3, name: 'React Frontend Developer', code: 'CH_RFT_2026', status: 'ongoing' }
  ];

  return (
    <div>
      <h2>Cohort Details</h2>
      <div>
        {cohorts.map(cohort => (
          <div key={cohort.id} className={styles.box}>
            <h3 style={{ color: cohort.status === 'ongoing' ? 'green' : 'blue' }}>
              {cohort.name}
            </h3>
            <dl>
              <dt>Cohort Code</dt>
              <dd>{cohort.code}</dd>
              <dt>Status</dt>
              <dd>{cohort.status}</dd>
            </dl>
          </div>
        ))}
      </div>
    </div>
  );
}
